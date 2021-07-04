package com.example.AccountingOfficeManagerServer.Entity.Documents;

import com.example.AccountingOfficeManagerServer.Entity.Client.Client;
import com.example.AccountingOfficeManagerServer.Entity.Company.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "Documents")
@Inheritance(strategy = InheritanceType.JOINED)
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int document_id;
    private String path;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference(value="company_document")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference(value="client_document")
    private Client client;

    public Document() {
    }

    public Document(int document_id, String path, Company company, Client client) {
        this.document_id = document_id;
        this.path = path;
        this.company = company;
        this.client = client;
    }

    public int getDocument_id() {
        return document_id;
    }

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
