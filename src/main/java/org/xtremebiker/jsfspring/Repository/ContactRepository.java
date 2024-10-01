package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.view.Contact;

// Data Access Layer
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
