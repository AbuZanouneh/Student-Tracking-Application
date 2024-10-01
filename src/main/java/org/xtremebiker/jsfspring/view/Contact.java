package org.xtremebiker.jsfspring.view;

import javax.persistence.*;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name" , length = 50, nullable = false)
    private String name;

    @Column(name="email" , length = 50, nullable = false)
    private String email;

    @Column(name="message" , nullable = false)
    private String message;

    public Contact() {
    }

    public Contact(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
