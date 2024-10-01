package org.xtremebiker.jsfspring.view;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name" , length = 45 ,nullable = false)
    private String firstName;

    @Column(name="last_name" , length = 45 ,nullable = false)
    private String lastName;

    @Column(name="email" , length = 45 ,nullable = false)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

}
