package org.app.service;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.app.requests.LeadRequest;
import org.app.entites.Lead;
import org.app.events.LeadCreatedEvent;
import org.app.repository.LeadRepository;
import org.app.response.LeadResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class LeadService {
    private LeadRepository leadRepository;
    private ApplicationEventPublisher eventPublisher;

    public LeadService(LeadRepository leadRepository, ApplicationEventPublisher eventPublisher) {
        this.leadRepository = leadRepository;
        this.eventPublisher = eventPublisher;
    }

    @RateLimiter(name = "leadServiceRateLimiter", fallbackMethod = "rateLimitFallback")
    public LeadResponse createLead(LeadRequest leadRequest){
        try {
            if (leadRepository.existsByContact(leadRequest.getContact())) {
                return new LeadResponse(false, "Phone number already exists", null);
            }

            if (leadRequest.getEmail()!= null && leadRepository.existsByEmail(leadRequest.getEmail())) {
                return new LeadResponse(false, "Email already exists", null);
            }
            Lead lead = getLead(leadRequest);
            lead = leadRepository.save(lead);
            eventPublisher.publishEvent(new LeadCreatedEvent(lead));

            return new LeadResponse(
                    true,
                    "Lead created successfully",
                    lead.getId()
            );
        } catch (Exception e) {
            System.err.println("Error creating lead: " + e.getMessage());
            e.printStackTrace();
            return new LeadResponse(
                    false,
                    "Failed to create lead: " + e.getMessage(),
                    null
            );
        }
    }

    public LeadResponse rateLimitFallback(LeadRequest leadRequest, Throwable t) {
        return new LeadResponse(false, "Too many requests! Please try again later.", null);
    }
    private Lead getLead(LeadRequest leadRequest) {
        Lead lead = new Lead();
        lead.setName(leadRequest.getName());
        lead.setContact(leadRequest.getContact());
        lead.setEmail(leadRequest.getEmail());
        lead.setOccupation(leadRequest.getOccupation());
        lead.setAdminNotified(leadRequest.isAdminNotified());
        return lead;
    }
}
