package org.app.entites;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "contact")
    private String contact;
    @Column(name = "email")
    private String email;
    @Column(name = "occupation")
    private String occupation;

    @Column(name = "is_admin_notified")
    private boolean isAdminNotified;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(java.lang.Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(java.lang.String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(java.lang.String occupation) {
        this.occupation = occupation;
    }

    public boolean isAdminNotified() {
        return isAdminNotified;
    }

    public void setAdminNotified(boolean adminNotified) {
        isAdminNotified = adminNotified;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Constructors
    public Lead() {
    }
//
//    public Lead(String name, String contact, String email, String occupation, boolean isAdminNotified) {
//        this.name = name;
//        this.contact = contact;
//        this.email = email;
//        this.occupation = occupation;
//        this.isAdminNotified = isAdminNotified;
//    }
}