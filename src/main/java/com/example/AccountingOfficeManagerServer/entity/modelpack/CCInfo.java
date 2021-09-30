package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.Company;

import java.util.List;

public class CCInfo {
    private Company company;
    private List<Client> clients;

    public CCInfo() {
    }

    public CCInfo(Company company, List<Client> clients) {
        this.company = company;
        this.clients = clients;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "{" +
                "\"company\": " + company +
                ", \"clients\": " + clients +
                '}';
    }
}
