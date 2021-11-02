package com.yevheniimakar.beltcutting.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private int id;

    @Column(nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
