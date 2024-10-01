package org.xtremebiker.jsfspring.view;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;


@ManagedBean
@RequestScoped
public class LoginBean {

    @Autowired
    private AuthenticationManager authenticationManager;

    private String username;
    private String password;


    public String login() {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //For Redirect based on role
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "/ui/index.xhtml?faces-redirect=true"; // index page - Admin
            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_TEACHER"))) {
                return "/ui/teacher-profile.xhtml?faces-redirect=true"; // Teacher profile
            } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"))) {
                return "/ui/student-profile.xhtml?faces-redirect=true"; // Student profile
            } else {
                return "/ui/home.xhtml?faces-redirect=true"; // Default redirect
            }
        } catch (AuthenticationException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", null));
            return null;
        }
    }


    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}