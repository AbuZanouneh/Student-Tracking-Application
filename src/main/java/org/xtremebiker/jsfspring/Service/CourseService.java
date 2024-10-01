package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.CourseRepository;
import org.xtremebiker.jsfspring.view.Course;

import java.util.List;

// Service Layer
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Retrieve all courses
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    // Save a course to the database
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    // Delete a course from the database by Id
    public void deleteCourseById(Long courseId) {
        courseRepository.deleteById(courseId);
    }


    // Find a course by its id
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
