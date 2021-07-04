package com.example.AccountingOfficeManagerServer.Entity.AccountingOffice;

import com.example.AccountingOfficeManagerServer.Entity.ClientCompany.ClientCompany;
import com.example.AccountingOfficeManagerServer.Entity.Company.Company;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounting_office")
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
}
