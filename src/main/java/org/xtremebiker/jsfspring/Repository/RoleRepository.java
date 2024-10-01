package org.xtremebiker.jsfspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xtremebiker.jsfspring.view.Role;

// Data Access Layer
public interface RoleRepository extends JpaRepository<Role, Integer> {

    // Method to find a role by its name
    Role findByRoleName(String roleName);

}


