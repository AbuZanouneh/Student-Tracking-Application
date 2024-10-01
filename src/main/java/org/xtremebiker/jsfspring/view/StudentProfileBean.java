package org.xtremebiker.jsfspring.view;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Service.StudentService;
import org.xtremebiker.jsfspring.Service.UserService;


@ManagedBean
@ViewScoped
@Component
public class StudentProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    private Student student;
    private List<Course> courses;

    @PostConstruct
    public void init() {
        String username = userService.getLoggedInUsername();
        if (username != null) {
            User user = userService.findByUsername(username);
            if (user != null && user.getRole() != null && user.getRole().getRoleName().equalsIgnoreCase("STUDENT")) {
                student = studentService.findStudentByUsername(username);
                if (student != null) {
                    // new ArrayList from student courses
                    courses = new ArrayList<>(student.getCourses());
                    // unmodifiable list
                    courses = Collections.unmodifiableList(courses);
                }
            }
        }
    }

//    @PostConstruct
//    public void init() {
//        String username = userService.getLoggedInUsername(); // Get the logged-in username
//        if (username != null) {
//            User user = userService.findByUsername(username); // Find the user by username
//            if (user != null && user.getRole() != null && user.getRole().getRoleName().equalsIgnoreCase("STUDENT")) {
//                // Fetch the student based on the email (username)
//                student = studentService.findStudentByEmail(user.getUsername()); // Assuming email matches username
//                if (student != null) {
//                    // Create a new ArrayList from the student's courses
//                    courses = new ArrayList<>(student.getCourses());
//                    // Make the list unmodifiable
//                    courses = Collections.unmodifiableList(courses);
//                }
//            }
//        }
//    }

    // Getters
    public Student getStudent() {
        return student;
    }

    public List<Course> getCourses() {
        return courses;
    }

    //Logout Method
    public String logout() {
        return "/ui/home.xhtml?faces-redirect=true";
    }
}