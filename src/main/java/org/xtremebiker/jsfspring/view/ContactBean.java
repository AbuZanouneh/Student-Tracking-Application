package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Repository.ContactRepository;
import org.xtremebiker.jsfspring.Service.ContactService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@Component
public class ContactBean implements Serializable {


    private Contact contact;
    private List<Contact> contactList;
    private final ContactRepository contactRepository;

    public ContactBean(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
        contact = new Contact();
    }

    @PostConstruct
    public void init() {
        loadContactList();
    }

    public void submitContact() {

        contactRepository.save(contact);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Your message was submitted successfully!", null));

        // Reload the contact list to include the newly added contact
        loadContactList(); // Load the contact list again after submission

        // Reset the contact object
        contact = new Contact();



    }

    public void loadContactList() {
        contactList = contactRepository.findAll();
    }

    // Getters and Setters
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Contact> getContactList() {
        return contactList;
    }


}
