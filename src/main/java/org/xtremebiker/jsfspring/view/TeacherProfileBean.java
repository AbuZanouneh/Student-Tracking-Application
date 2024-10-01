package org.xtremebiker.jsfspring.view;


import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xtremebiker.jsfspring.Service.TeacherService;
import org.xtremebiker.jsfspring.Service.UserService;
import org.xtremebiker.jsfspring.view.User;
import org.xtremebiker.jsfspring.view.Role;


//@Named("teacherProfileBean")
@ManagedBean
@ViewScoped
@Component
public class TeacherProfileBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    private Teacher teacher;

    private List<Course> courses;

    @PostConstruct
    public void init() {
        String username = userService.getLoggedInUsername();
        if (username != null) {
            User user = userService.findByUsername(username);
            if (user != null && user.getRole() != null && user.getRole().getRoleName().equalsIgnoreCase("TEACHER")) {
                // Fetch the teacher based on the username
                teacher = teacherService.findTeacherByUsername(username);
                if (teacher != null) {
                    courses = teacherService.findCoursesByTeacherId(teacher.getTeacherId());
                }
            }
        }
    }

    // Getters and logout method
}