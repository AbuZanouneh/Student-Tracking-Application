package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.CourseRepository;
import org.xtremebiker.jsfspring.Repository.StudentRepository;
import org.xtremebiker.jsfspring.view.Course;
import org.xtremebiker.jsfspring.view.Student;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

// Service Layer
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // To register Maximum 7 Courses
    private static final int MAX_COURSES_PER_STUDENT = 7;

    // This method retrieve all students list from the db.
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    // This method saves the student to the db.
    @Transactional
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    // This method deletes the student from the database
    @Transactional
    public void deleteStudentById(Integer StudentId) {
        studentRepository.deleteById(StudentId);
    }

    // Fetching the student from the repository
    public Student findStudentById(Integer StudentId) {
        return studentRepository.findById(StudentId).orElse(null);
    }

    public Student findStudentWithCourses(Integer id) {
        return studentRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    // For register student into a specific course.
    @Transactional
    public void registerStudentForCourse(Integer studentId, Long courseId) throws Exception {
        Student student = findStudentWithCourses(studentId);
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        if (student.getCourses().size() >= MAX_COURSES_PER_STUDENT) {
            throw new Exception("Maximum number of course registrations reached!!");
        }

        student.addCourse(course);
        studentRepository.save(student);
    }

    //For unregister student from a specific course.
    @Transactional
    public void unregisterStudentFromCourse(Integer studentId, Long courseId) {
        Student student = studentRepository.findByIdWithCourses(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        student.removeCourse(course);
        studentRepository.save(student);
    }

    public Student findStudentByUsername(String username) {
        return studentRepository.findByEmail(username);
    }

    // Find a student by email
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }


}
