package com.example.AccountingOfficeManagerServer.Entity.User;

import com.example.AccountingOfficeManagerServer.Entity.Client.Client;
import com.example.AccountingOfficeManagerServer.Entity.Company.Company;
import com.example.AccountingOfficeManagerServer.Entity.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String first_name;
    private String last_name;
    private String mail;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference(value="company")
    private Company company;

    public User(int user_id, String first_name, String last_name, String mail) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
    }

    public User() {
    }

    public User(int user_id, String first_name, String last_name, String mail, Company company) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mail = mail;
        this.company = company;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
