package org.xtremebiker.jsfspring.view;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "advertisements")
public class Advertisements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    @Column(name = "date_posted", nullable = false)
    private LocalDate datePosted; // LocalDate

    //Automatically set the date when created
    public Advertisements() {
        this.datePosted = LocalDate.now(); }

    public Advertisements(String title, String description) {
        this.title = title;
        this.description = description;
        this.datePosted = LocalDate.now(); //Automatically set the date when created
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }
}