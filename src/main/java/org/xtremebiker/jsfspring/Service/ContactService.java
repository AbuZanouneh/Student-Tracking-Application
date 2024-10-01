package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.ContactRepository;
import org.xtremebiker.jsfspring.view.Contact;

import java.util.List;

//Service Layer
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public void saveContact(Contact contact) {
        contactRepository.save(contact);
    }


}
