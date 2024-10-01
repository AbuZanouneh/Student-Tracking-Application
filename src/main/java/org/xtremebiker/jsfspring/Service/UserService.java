package org.xtremebiker.jsfspring.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xtremebiker.jsfspring.Repository.UserRepository;
import org.xtremebiker.jsfspring.view.Role;
import org.xtremebiker.jsfspring.view.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

// Service Layer
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    /**
//     * Retrieves the username of the currently authenticated user.
//     *
//     * @return the username if authenticated, otherwise null
//     */
    public String getLoggedInUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.getPrincipal() instanceof UserDetails){
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }

        return null;
    }

//    /**
//     * Finds a User entity by username.
//     *
//     * @param username the username to search for
//     * @return the User entity if found, otherwise null
//     */
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

//    /**
//     * Saves a User entity.
//     *
//     * @param user the User entity to save
//     */
    // Save a user
    public void saveUser(User user){
        userRepository.save(user);
    }

    // Delete a user
    public void deleteUser(User user) { userRepository.delete(user); }

    // Retrieve all roles
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
