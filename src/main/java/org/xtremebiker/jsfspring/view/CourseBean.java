package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Service.CourseService;
import org.xtremebiker.jsfspring.Service.TeacherService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.util.List;

@ManagedBean
@ViewScoped
@Component
public class CourseBean {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    // For creating/updating a course (course handling)
    private Course course = new Course();

    // To hold selected teacher ID
    private Long selectedTeacherId;

    private List<Course> availableCourses;

    // Getters and Setters
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getSelectedTeacherId() {
        return selectedTeacherId;
    }

    public void setSelectedTeacherId(Long selectedTeacherId) {
        this.selectedTeacherId = selectedTeacherId;
    }

    @PostConstruct
    public void init() {
        loadAvailableCourses();
    }

    // This Method to retrieve all courses
    public List<Course> getCourses() {
        return courseService.findAllCourses();
    }

    // This Method to retrieve all teachers for dropdown
    public List<Teacher> getTeachers() {
        return teacherService.findAllTeachers();
    }

    public void loadAvailableCourses() {
        availableCourses = courseService.findAllCourses();
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }

    //This Method to save a course (create or update)
    public String saveCourse() {
        // Assign selected teacher to the course before save operation.
        if (selectedTeacherId != null) {
            Teacher assignedTeacher = new Teacher();
            assignedTeacher.setTeacherId(selectedTeacherId);
            course.setTeacher(assignedTeacher);
        }
        courseService.saveCourse(course);
        course = new Course(); // Reset after saving
        return "courses.xhtml?faces-redirect=true";
    }


    // Thsi Method to delete a course by it's Id
    public void deleteCourse(Long courseId) {
        courseService.deleteCourseById(courseId);
    }

    // Method to prepare a course for update - redirect to update-course.xhtml page.
    public void prepareUpdate(Long courseId) {
        this.course = courseService.findCourseById(courseId);
        if (course != null && course.getTeacher() != null) {
            this.selectedTeacherId = course.getTeacher().getTeacherId(); // for the dropdown list in update-course.xhtml page.
        }
    }

    // This Method to update a specific course details.
    public String updateCourse() {
        if (selectedTeacherId != null) {
            Teacher assignedTeacher = new Teacher();
            assignedTeacher.setTeacherId(selectedTeacherId);
            course.setTeacher(assignedTeacher);
        }
        courseService.saveCourse(course);
        return "courses.xhtml?faces-redirect=true"; // Redirect to courses page.
    }



}
