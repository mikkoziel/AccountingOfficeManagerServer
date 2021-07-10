package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name = "client_company")
public class ClientCompany extends Company {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_id")
    @JsonBackReference(value="clients")
    private AccountingOffice accounting_office;

    public ClientCompany(int company_id, String name) {
        super(company_id, name);
    }

    public ClientCompany() {
    }
}
