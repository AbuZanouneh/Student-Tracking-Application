package org.xtremebiker.jsfspring.view;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", length = 50, nullable = false, unique = true)
    private String roleName; // We got ADMIN, TEACHER, STUDENT.

    // Bi-directional relationship with User
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}