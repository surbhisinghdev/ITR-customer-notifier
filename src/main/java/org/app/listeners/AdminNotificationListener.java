package org.app.listeners;

import org.app.entites.Lead;
import org.app.events.LeadCreatedEvent;
import org.app.repository.LeadRepository;
import org.app.requests.NotificationRequest;
import org.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Component
public class AdminNotificationListener {

    @Value("${admin.notification.email}")
    private String adminEmail;
    @Value("${admin.notification.email.title}")
    private String title;

    private NotificationService notificationService;
    private LeadRepository leadRepository;

    public AdminNotificationListener(NotificationService notificationService, LeadRepository leadRepository) {
        this.notificationService = notificationService;
        this.leadRepository = leadRepository;
    }

    @EventListener
    @Async
    public void onLeadCreated(LeadCreatedEvent event) {
        Lead lead = event.getLead();

        NotificationRequest nr = getNotificationRequest(lead);

        try {
//            System.out.println("Running sendEmail on thread: {}" +Thread.currentThread().getName());
            notificationService.notify(nr);  // send email
            // After success, mark lead as notified
            lead.setAdminNotified(true);
            leadRepository.save(lead);
        } catch (Exception e) {
            // handle failure (log, retry, etc.)
            System.err.println("Failed to notify admin: " + e.getMessage());
        }
    }

    private NotificationRequest getNotificationRequest(Lead lead) {
        NotificationRequest notificationRequest = new NotificationRequest();
        notificationRequest.setTitle(title);
        notificationRequest.setRecipient(adminEmail);
        notificationRequest.setMessage(
                "Hello Admin,\n\n" +
                        "A new lead has been submitted on your platform.\n\n" +
                        "Name: " + lead.getName() + "\n" +
                        "Contact: " + lead.getContact() + "\n" +
                        "Email: " + (lead.getEmail() != null ? lead.getEmail() : "Not provided") + "\n" +
                        "Occupation: " + (lead.getOccupation() != null ? lead.getOccupation() : "Not provided") + "\n\n" +
                        "Please follow up with the lead at your earliest convenience.\n\n" +
                        "Thank you,\n" +
                        "Your Company Team"
        );
//        notificationRequest.setMessage(
//                "Hello Hottie,\n\n" +
//                        "Mera jism tujhe chahe pana.\n\n" +
//                        "Bus tujhe panna.\n\n" +
//                        "Aaja meri baahone me...\n\n" +
//                        "Ho jaye ye raat gufatagu and pade mere jism ko thandak.\n\n " +
//                        "Your secert admirer,\n"
//        );
        return notificationRequest;
    }
}

