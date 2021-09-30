package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="role_id")
public class Role implements GrantedAuthority, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int role_id;
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    public Role(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return null;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int id) {
        this.role_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void removeUser(User user) {this.users.remove(user);}

    @Override
    public String toString() {
        return "{" +
                "\"name\": '" + name + '\'' +
                '}';
    }
}

