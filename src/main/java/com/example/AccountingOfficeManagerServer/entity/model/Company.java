package com.example.AccountingOfficeManagerServer.entity.model;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="company_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int company_id;
    protected String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference(value="company")
    protected List<User> users = new ArrayList<>();

    public Company(int company_id, String name) {
        this.company_id = company_id;
        this.name = name;
    }

    public Company() {
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUsers(User user) {
        this.users.add(user);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Company{" +
                "\"company_id\": " + company_id +
                ", \"name\": '" + name + '\'' +
                ", \"users\": " + users +
                '}';
    }
}
