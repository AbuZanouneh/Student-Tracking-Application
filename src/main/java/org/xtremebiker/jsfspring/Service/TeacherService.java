package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.CourseRepository;
import org.xtremebiker.jsfspring.Repository.TeacherRepository;
import org.xtremebiker.jsfspring.view.Course;
import org.xtremebiker.jsfspring.view.Teacher;

import java.util.List;

// Service Layer
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRepository courseRepository;


    // Retrieve all teachers list from the db.
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    // Save a teacher to the db.
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    // Delete a teacher from the db by its id
    public void deleteTeacherById(Long teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    // Find a teacher by Id.
    public Teacher findTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }

    // Method to find courses assigned to a specific teacher
    public List<Course> findCoursesByTeacherId(Long teacherId) {
        return courseRepository.findByTeacher_TeacherId(teacherId);
    }
    // Assume that the username corresponds to the email in the teachers table.
    public Teacher findTeacherByUsername(String username) {
        return teacherRepository.findByEmail(username);
    }

}
