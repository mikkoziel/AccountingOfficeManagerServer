package com.example.AccountingOfficeManagerServer.entity.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="user_id")
public class Employee extends User implements Serializable {

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
//    @JsonManagedReference(value="admin")
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
//    @JsonBackReference(value="admin")
    private Employee admin;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//    @JsonManagedReference(value="client")
    private List<Client> clients = new ArrayList<>();

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
//    @JsonManagedReference(value="worklog")
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

    @Override
    public String toString() {
        return "Employee{" +
                "\"user_id\": " + user_id +
                ", \"first_name\": '" + first_name + '\'' +
                ", \"last_name\": '" + last_name + '\'' +
                ", \"username\": '" + username + '\'' +
//                ", \"password\": '" + password + '\'' +
                ", \"company\": " + company.getName() +
                ", \"roles\": " + roles +
                ", \"employees\": " + employees.stream().map(Employee::getUser_id).collect(Collectors.toList()) +
                ", \"admin\": " + admin.getUser_id() +
                ", \"clients\": " + clients.stream().map(Client::getUser_id).collect(Collectors.toList()) +
                ", \"worklog\": " + worklog.stream().map(WorkLog::getWorklog_id).collect(Collectors.toList()) +
                '}';
    }
}
