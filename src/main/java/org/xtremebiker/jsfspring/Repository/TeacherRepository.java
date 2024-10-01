package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.view.Teacher;

// Data Access Layer
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByEmail(String email);
}
