package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import org.xtremebiker.jsfspring.Repository.CourseRepository;
import org.xtremebiker.jsfspring.Service.TeacherService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.List;
import java.util.stream.Collectors;
import java.io.Serializable;
import java.util.List;


@ManagedBean
@ViewScoped
@Component
public class TeacherBean {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseRepository courseRepository;

    private Teacher teacher = new Teacher();

    private List<Course> courses; // To hold courses assigned to the teacher

    // Getters and Setters
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // retrieve all teachers
    public List<Teacher> getTeachers() {
        return teacherService.findAllTeachers();
    }

    // save a teacher
    public String saveTeacher() {
        teacherService.saveTeacher(teacher);
        teacher = new Teacher();
        return "teachers.xhtml?faces-redirect=true";
    }

    // Delete a teacher by it's Id
    public void deleteTeacher(Long teacherId) {
        teacherService.deleteTeacherById(teacherId);
    }

    // prepare a teacher for update
    public void prepareUpdate(Long teacherId) {
        this.teacher = teacherService.findTeacherById(teacherId);
    }

    // Method to update a teacher's details
    public String updateTeacher() {
        teacherService.saveTeacher(teacher);
        return "teachers.xhtml?faces-redirect=true";
    }


    // Method to prepare and fetch teacher details and their assigned courses
    public void prepareView(Long teacherId) {
        System.out.println("Fetching details for Teacher ID:" + teacherId);
        this.teacher = teacherService.findTeacherById(teacherId);
        this.courses = courseRepository.findByTeacher_TeacherId(teacherId);
            }


    // Reset after saving or updating
    public void resetTeacher() {
        this.teacher = new Teacher();
    }


}
