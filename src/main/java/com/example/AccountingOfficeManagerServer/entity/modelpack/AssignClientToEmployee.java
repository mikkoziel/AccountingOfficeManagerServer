package com.example.AccountingOfficeManagerServer.entity.modelpack;

public class AssignClientToEmployee {
    private Integer employee_id;
    private Integer client_id;

    public AssignClientToEmployee() {
    }

    public AssignClientToEmployee(Integer employee_id, Integer client_id) {
        this.employee_id = employee_id;
        this.client_id = client_id;
    }

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public Integer getClient_id() {
        return client_id;
    }

    public void setClient_id(Integer client_id) {
        this.client_id = client_id;
    }
}
