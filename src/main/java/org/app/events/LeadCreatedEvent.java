package org.app.events;

import org.app.entites.Lead;

public class LeadCreatedEvent {
    private final Lead lead;

    public LeadCreatedEvent(Lead lead) {
        this.lead = lead;
    }

    public Lead getLead() {
        return lead;
    }
}

