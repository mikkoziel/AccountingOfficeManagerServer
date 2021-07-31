package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "documents")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="document_id")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int document_id;
    private String path;
    private String description;

    @ManyToOne
    @JoinColumn(name = "company_id")
//    @JsonBackReference(value="company_document")
    private ClientCompany company;

    @ManyToOne
    @JoinColumn(name = "client_id")
//    @JsonBackReference(value="client_document")
    private Client client;


    public Document() {
    }

    public Document(ClientCompany company, Client client) {
        this.company = company;
        this.client = client;
    }

    public Document(String path, ClientCompany company, Client client) {
        this.path = path;
        this.company = company;
        this.client = client;
    }

    public Document(int document_id, String path, ClientCompany company, Client client) {
        this.document_id = document_id;
        this.path = path;
        this.company = company;
        this.client = client;
    }

    public Document(int document_id, String path, ClientCompany company, Client client, String description) {
        this.document_id = document_id;
        this.path = path;
        this.company = company;
        this.client = client;
        this.description = description;
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

    public ClientCompany getCompany() {
        return company;
    }

    public void setCompany(ClientCompany company) {
        this.company = company;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Document{" +
                "\"document_id\": " + document_id +
                ", \"path\": '" + path + '\'' +
                ", \"company\": " + company +
                ", \"client\": " + client +
                ", \"description\": '" + description + '\'' +
                '}';
    }
}
