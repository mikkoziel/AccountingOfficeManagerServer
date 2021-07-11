package com.example.AccountingOfficeManagerServer.entity.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee extends User{

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    @JsonManagedReference(value="admin")
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    @JsonBackReference(value="admin")
    private Employee admin;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference(value="client")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonManagedReference(value="worklog")
    private List<WorkLog> worklog = new ArrayList<>();

    public Employee() {
        super();
    }

    public Employee(int user_id, String first_name, String last_name, String mail, Company company, Employee admin, String password, List<Role> roles) {
        super(user_id, first_name, last_name, mail, password, company, roles);
        this.admin = admin;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public List<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public Employee getAdmin() {
        return admin;
    }

    public void setAdmin(Employee admin) {
        this.admin = admin;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<WorkLog> getWorklog() {
        return worklog;
    }

    public void setWorklog(List<WorkLog> worklog) {
        this.worklog = worklog;
    }
}
