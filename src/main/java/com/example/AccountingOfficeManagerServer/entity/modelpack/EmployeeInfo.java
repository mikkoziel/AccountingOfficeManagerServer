package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.Employee;

import java.util.List;

public class EmployeeInfo {
    private Employee employee;
    private List<Client> clients;
    private List<Employee> employees;
    private List<Client> adminClients;

    public EmployeeInfo() {
    }

    public EmployeeInfo(Employee employee, List<Client> clients, List<Employee> employees, List<Client> adminClients) {
        this.employee = employee;
        this.clients = clients;
        this.employees = employees;
        this.adminClients = adminClients;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Client> getAdminClients() {
        return adminClients;
    }

    public void setAdminClients(List<Client> adminClients) {
        this.adminClients = adminClients;
    }

    @Override
    public String toString() {
        return "{" +
                "\"employee\": " + employee +
                ", \"clients\": " + clients +
                ", \"employees\": " + employees +
                ", \"adminClients\": " + adminClients +
                '}';
    }
}
