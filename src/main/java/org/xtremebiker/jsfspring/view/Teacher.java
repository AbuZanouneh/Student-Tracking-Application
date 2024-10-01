package org.xtremebiker.jsfspring.view;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name", length = 50, nullable = false)
    private String teacherName;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 15)
    private String phoneNumber;

    // OnetoMany relationship with Course
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Course> courses;


    public Teacher() {}

    public Teacher(String teacherName, String email, String phoneNumber) {
        this.teacherName = teacherName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
