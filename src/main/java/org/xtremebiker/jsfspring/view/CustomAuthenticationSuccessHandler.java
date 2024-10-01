package org.xtremebiker.jsfspring.view;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectURL = request.getContextPath();

        for (GrantedAuthority authority : authorities){
            if(authority.getAuthority().equals("ROLE_ADMIN")){
                redirectURL = "/ui/index.xhtml";
                break;
            }
            else if(authority.getAuthority().equals("ROLE_TEACHER")){
                redirectURL = "/ui/teacher/teacher-profile.xhtml";
                break;
            }
            else if(authority.getAuthority().equals("ROLE_STUDENT")){
                redirectURL = "/ui/student/student-profile.xhtml";
                break;
            }
        }

        response.sendRedirect(redirectURL);
    }
}