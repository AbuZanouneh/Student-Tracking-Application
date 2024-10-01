package org.xtremebiker.jsfspring.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xtremebiker.jsfspring.Service.RoleService;
import org.xtremebiker.jsfspring.Service.UserService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.security.SecureRandom;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String username;
    private Integer selectedRoleId;
    private List<Role> roles;
    private List<User> users;

    @PostConstruct
    public void init() {
        roles = roleService.findAllRoles();
        users = userService.findAllUsers();
    }

    public void addUser() {
        if (username != null && selectedRoleId != null) {
            String generatedPassword = UUID.randomUUID().toString(); // Generate a random password
            String encryptedPassword = passwordEncoder.encode(generatedPassword); // Encrypt the password

            Role role = roleService.findRoleById(selectedRoleId);
            User user = new User(username, encryptedPassword, role);
            userService.saveUser(user);

            users = userService.findAllUsers(); // To refresh the list
            username = null; // Reset the form
            selectedRoleId = null;
        }
    }

    // This method to delete a user
    public void deleteUser(User user) {
        if (user != null) {
            userService.deleteUser(user);
            users = userService.findAllUsers(); // Refresh the list after deletion
        }
    }

    // to delete
    // Method to generate a random password for display purposes
    public String generateRandomPassword() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSelectedRoleId() {
        return selectedRoleId;
    }

    public void setSelectedRoleId(Integer selectedRoleId) {
        this.selectedRoleId = selectedRoleId;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public List<User> getUsers() {

        //return users;
        // Here, create a copy or modify the User list temporarily to include a random password for display
        return users.stream()
                .map(user -> {
                    user.setPassword(generateRandomPassword()); // Replace password with random
                    return user;
                })
                .collect(Collectors.toList());
    }
}