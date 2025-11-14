package org.app.controller;

import jakarta.validation.Valid;
import org.app.requests.LeadRequest;
import org.app.response.LeadResponse;
import org.app.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/leads/")
public class LeadController {
    LeadService leadService;
    LeadController(LeadService leadService) {
        this.leadService = leadService;
    }
    @PostMapping
    public ResponseEntity<LeadResponse> createLead(@Valid @RequestBody LeadRequest leadRequest){
        LeadResponse leadResponse =  leadService.createLead(leadRequest);
        HttpStatus status = leadResponse.isSuccess() ? HttpStatus.CREATED :
                leadResponse.getMessage().contains("exists") ? HttpStatus.CONFLICT :
                        HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(status).body(leadResponse);
    }
}
