package com.example.AccountingOfficeManagerServer.entity.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "client")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="user_id")
public class Client extends User implements Serializable {

    @ManyToOne
    @JoinColumn(name = "employee_id")
//    @JsonBackReference(value="client")
    private Employee employee;


    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//    @JsonManagedReference(value="client_document")
    private List<Document> documents = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(int user_id, String first_name, String last_name, String mail, String password, Company company, List<Role> roles) {
        super(user_id, first_name, last_name, mail, password, company, roles);
    }


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "{" +
                " \"user_id\": " + user_id +
                ", \"first_name\": '" + first_name + '\'' +
                ", \"last_name\": '" + last_name + '\'' +
                ", \"username\": '" + username + '\'' +
//                ", \"password\": '" + password + '\'' +
                ", \"company\": " + company +
                ", \"roles\": " + roles +
//                ", \"employee\": " + employee.getUser_id() +
                ", \"documents\": " + documents.stream().map(Document::getDocument_id).collect(Collectors.toList()) +
                '}';
    }
}
