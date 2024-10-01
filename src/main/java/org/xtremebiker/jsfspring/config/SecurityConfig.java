package org.xtremebiker.jsfspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.xtremebiker.jsfspring.Service.UserDetailsServiceImpl;
import org.xtremebiker.jsfspring.view.CustomAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // Allow access to login, home, view-advertisement, and about pages without authentication
                .antMatchers("/ui/login.xhtml", "/ui/contact.xhtml", "/ui/about.xhtml", "/ui/home.xhtml", "/ui/view-advertisement.xhtml", "/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/ui/login.xhtml")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/ui/index.xhtml")
                .failureUrl("/ui/login.xhtml?error=true")
                .and()
                .logout()
                .logoutSuccessUrl("/ui/home.xhtml")
                .and()
                .csrf().disable();
    }

//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/ui/login.xhtml", "/ui/home.xhtml", "/javax.faces.resource/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/ui/login.xhtml")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/ui/index.xhtml")
//                .failureUrl("/ui/login.xhtml?error=true")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/ui/home.xhtml")
//                .and()
//                .csrf().disable();
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}