package com.example.AccountingOfficeManagerServer.Entity.Client;
import com.example.AccountingOfficeManagerServer.Entity.Employee.Employee;
import com.example.AccountingOfficeManagerServer.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client extends User{

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @JsonBackReference
    private Employee employee;

    public Client(int user_id, String first_name, String last_name, String mail) {
        super(user_id, first_name, last_name, mail);
    }

    public Client() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
