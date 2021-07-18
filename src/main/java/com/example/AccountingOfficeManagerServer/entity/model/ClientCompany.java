package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client_company")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="company_id")
public class ClientCompany extends Company {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_id")
    @JsonBackReference(value="clients")
    private AccountingOffice accounting_office;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonManagedReference(value="company_document")
    protected List<Document> documents = new ArrayList<>();

    public ClientCompany(int company_id, String name) {
        super(company_id, name);
    }

    public ClientCompany() {
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "ClientCompany{" +
                "\"company_id\": " + company_id +
                ", \"name\": '" + name + '\'' +
                ", \"users\": " + users +
                ", \"accounting_office\": " + accounting_office +
                ", \"documents\": " + documents +
                '}';
    }
}
