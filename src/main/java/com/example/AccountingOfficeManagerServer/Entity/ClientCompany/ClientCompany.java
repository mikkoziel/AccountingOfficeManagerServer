package com.example.AccountingOfficeManagerServer.Entity.ClientCompany;

import com.example.AccountingOfficeManagerServer.Entity.AccountingOffice.AccountingOffice;
import com.example.AccountingOfficeManagerServer.Entity.Company.Company;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name = "client_company")
public class ClientCompany extends Company {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ao_id")
    @JsonBackReference
    private AccountingOffice accounting_office;

    public ClientCompany(int company_id, String name) {
        super(company_id, name);
    }

    public ClientCompany() {
    }
}
