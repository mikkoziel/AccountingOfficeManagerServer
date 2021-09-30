package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "client_company")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="company_id")
public class ClientCompany extends Company implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_id")
//    @JsonBackReference(value="clients")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AccountingOffice accounting_office;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
//    @JsonManagedReference(value="company_document")
    protected List<Document> documents = new ArrayList<>();

    public ClientCompany(int company_id, String name) {
        super(company_id, name);
    }

    public ClientCompany() {
    }

    public AccountingOffice getAccounting_office() {
        return accounting_office;
    }

    public void setAccounting_office(AccountingOffice accounting_office) {
        this.accounting_office = accounting_office;
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
                "\"company_id\": " + company_id +
                ", \"name\": '" + name + '\'' +
                ", \"users\": " + users.stream().map(User::getUser_id).collect(Collectors.toList()) +
//                ", \"accounting_office\": " + accounting_office.getCompany_id() +
                ", \"documents\": " + documents.stream().map(Document::getDocument_id).collect(Collectors.toList()) +
                '}';
    }
}
