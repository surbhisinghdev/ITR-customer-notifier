package org.app.requests;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LeadRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "\\d{10}", message = "Contact must be 10 digits")
    private String contact;

    @Email(message = "Email must be valid")
    private String email;

    @Size(max = 50, message = "Occupation must be at most 50 characters")
    private String occupation;
    private boolean isAdminNotified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public boolean isAdminNotified() {
        return isAdminNotified;
    }

    public void setAdminNotified(boolean adminNotified) {
        isAdminNotified = adminNotified;
    }
}
