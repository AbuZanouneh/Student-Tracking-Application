package org.xtremebiker.jsfspring.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.RoleRepository;
import org.xtremebiker.jsfspring.view.Role;

import java.util.List;

// Service Layer
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Finds a Role by its name.
     *
     * @param roleName Name of the role (e.g., ADMIN, TEACHER, STUDENT)
     * @return Role entity if found, else null
     */
    public Role findByRoleName(String roleName){
        return roleRepository.findByRoleName(roleName);
    }

    /**
     * Saves a Role entity.
     *
     * @param role Role to be saved
     */
    public void saveRole(Role role){
        roleRepository.save(role);
    }

    // Retrieve all roles
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    // Find a role by ID
    public Role findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
