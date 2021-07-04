package com.example.AccountingOfficeManagerServer.Entity.Company;
import com.example.AccountingOfficeManagerServer.Entity.Documents.Document;
import com.example.AccountingOfficeManagerServer.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "company")
@Inheritance(strategy = InheritanceType.JOINED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int company_id;
    private String name;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference(value="company")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference(value="company_document")
    private List<Document> documents = new ArrayList<>();

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

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
