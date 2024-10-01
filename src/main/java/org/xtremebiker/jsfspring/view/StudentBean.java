package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Service.CourseService;
import org.xtremebiker.jsfspring.Service.StudentService;
import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@ManagedBean
@ViewScoped
@Component
public class StudentBean implements Serializable{

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private List<Student> students;

    private Student selectedStudent;

    private List<Course> studentCourses;

    private Long selectedCourseId;

    private int registeredCoursesCount;

    private Student student = new Student(); // For creating/updating a student (Student Handling)

    @PostConstruct
    public void init() {
        loadStudents();
    }

    // Getters and Setters
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    // This Method to retrieve all students list from db
    public List<Student> getStudents() {
        return studentService.findAllStudents();
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // This method to save a student
    public String saveStudent() {
        studentService.saveStudent(student);
        student = new Student();
        return "students.xhtml?faces-redirect=true";
    }

    // delete student by Id
    public void deleteStudent(Integer studentId) {
        studentService.deleteStudentById(studentId);
    }

    // Method to prepare a student for update - update-student page
    public void prepareUpdate(Integer studentId) {
        this.student = studentService.findStudentById(studentId);
    }

    // Update a student's
    public String updateStudent() {
        studentService.saveStudent(student);
        return "students.xhtml?faces-redirect=true";
    }


    // Reset after saving and updating
    public void resetStudent() {
        this.student = new Student();
    }


    public void loadStudents() {
        students = studentService.findAllStudents();
    }

    public String viewCourses(Integer studentId) {
        this.selectedStudent = studentService.findStudentWithCourses(studentId);
        this.studentCourses = new ArrayList<>(selectedStudent.getCourses());
        this.registeredCoursesCount = this.studentCourses.size();
        return "student_courses?faces-redirect=true";
    }

    public void registerForCourse() {
        try {
            studentService.registerStudentForCourse(selectedStudent.getId(), selectedCourseId);
            this.selectedStudent = studentService.findStudentWithCourses(selectedStudent.getId());
            this.studentCourses = new ArrayList<>(selectedStudent.getCourses());
            this.registeredCoursesCount = this.studentCourses.size();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Student registered for course"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    // To handle delete course registration.
    public void deleteCourseRegistration(Long courseId) {
        try {
            studentService.unregisterStudentFromCourse(selectedStudent.getId(), courseId);
            // Refresh the student's courses after deletion
            this.selectedStudent = studentService.findStudentWithCourses(selectedStudent.getId());
            this.studentCourses = new ArrayList<>(selectedStudent.getCourses());
            this.registeredCoursesCount = this.studentCourses.size(); // Update the count
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Course registration deleted."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete course registration."));
        }
    }

    // To delete a course that assigned to a specific user.
//    public String deleteCourseRegistration(Long courseId) {
//        try {
//            studentService.unregisterStudentFromCourse(selectedStudent.getId(), courseId);
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Course registration deleted."));
//            return "student_courses?faces-redirect=true&includeViewParams=true"; // Redirect to the same page
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Failed to delete course registration."));
//            return null; // Stay on the current page if there's an error
//        }
//    }

    // Add getter for registeredCoursesCount  to delete
    public int getRegisteredCoursesCount() {
        return registeredCoursesCount;
    }


    // Getters and setters for all fields
    public List<Course> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Long getSelectedCourseId() {
        return selectedCourseId;
    }

    public void setSelectedCourseId(Long selectedCourseId) {
        this.selectedCourseId = selectedCourseId;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    // For security
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        return null;
    }



}
