package org.app.repository;

import org.app.entites.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    boolean existsByContact(String contact);
    boolean existsByEmail(String email);
}
