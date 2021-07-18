package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounting_office")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="company_id")
public class AccountingOffice extends Company {

    @OneToMany(mappedBy = "accounting_office", fetch = FetchType.LAZY)
    @JsonManagedReference(value="clients")
    private List<ClientCompany> clientCompanies = new ArrayList<>();

    public AccountingOffice(int company_id, String name) {
        super(company_id, name);
    }

    public AccountingOffice() {
    }

    public List<ClientCompany> getClientCompanies() {
        return clientCompanies;
    }

    public void setClientCompanies(List<ClientCompany> clientCompanies) {
        this.clientCompanies = clientCompanies;
    }

    @Override
    public String toString() {
        return "AccountingOffice{" +
                "\"company_id\": " + company_id +
                ", \"name\": '" + name + '\'' +
                ", \"users\": " + users +
                ", \"clientCompanies\": " + clientCompanies +
                '}';
    }
}
