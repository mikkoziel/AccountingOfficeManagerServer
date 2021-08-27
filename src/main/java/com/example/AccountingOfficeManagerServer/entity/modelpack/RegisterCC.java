package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.ClientCompany;

public class RegisterCC {
    private ClientCompany cc;
    private int id;

    public RegisterCC() {
    }

    public RegisterCC(ClientCompany cc, int id) {
        this.cc = cc;
        this.id = id;
    }

    public ClientCompany getCc() {
        return cc;
    }

    public void setCc(ClientCompany cc) {
        this.cc = cc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
