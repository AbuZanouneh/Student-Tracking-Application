package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.xtremebiker.jsfspring.view.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Integer id);

    Student findByEmail(String email);

}
