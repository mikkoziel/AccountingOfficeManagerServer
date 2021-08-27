package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.AccountingOffice;
import com.example.AccountingOfficeManagerServer.entity.model.Employee;

public class RegisterAO {
    private AccountingOffice ao;
    private Employee employee;

    public RegisterAO() {
    }

    public RegisterAO(AccountingOffice ao, Employee employee) {
        this.ao = ao;
        this.employee = employee;
    }

    public AccountingOffice getAo() {
        return ao;
    }

    public void setAo(AccountingOffice ao) {
        this.ao = ao;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
