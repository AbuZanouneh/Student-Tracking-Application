package org.xtremebiker.jsfspring.view;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    @Column(name = "description", length = 255)
    private String description;

    // ManyToOne relationship with Teacher
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id") // Foreign key column in courses table
    private Teacher teacher; // reference to the teacher teaching this course

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Course() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }
}
