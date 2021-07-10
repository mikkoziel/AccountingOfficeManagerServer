package com.example.AccountingOfficeManagerServer.entity.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
public class Client extends User{

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference(value="client")
    private Employee employee;


    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @JsonManagedReference(value="client_document")
    private List<Document> documents = new ArrayList<>();


    public Client(int user_id, String first_name, String last_name, String mail, String password, Company company) {
        super(user_id, first_name, last_name, mail, password, company);
    }

    public Client() {
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
}
