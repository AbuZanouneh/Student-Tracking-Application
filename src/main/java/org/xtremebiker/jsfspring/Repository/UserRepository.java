package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.view.User;

// Data Access Layer
public interface UserRepository extends JpaRepository<User, Integer> {

    // This Method to find a user by their username
    User findByUsername(String username);

}


