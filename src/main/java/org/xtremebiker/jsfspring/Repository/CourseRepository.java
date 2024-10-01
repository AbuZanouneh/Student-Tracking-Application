package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.view.Course;

import java.util.List;

// Data Access Layer
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Method to find courses by teacher's Id
    List<Course> findByTeacher_TeacherId(Long teacherId);

}
