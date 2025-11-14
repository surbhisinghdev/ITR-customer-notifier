package org.app.response;

public class LeadResponse extends ApiResponse {
    private Long leadId;

    public LeadResponse() {}

    public LeadResponse(boolean success, String message, Long leadId) {
        super(success, message);
        this.leadId = leadId;
    }

    public Long getLeadId() { return leadId; }
    public void setLeadId(Long leadId) { this.leadId = leadId; }
}
