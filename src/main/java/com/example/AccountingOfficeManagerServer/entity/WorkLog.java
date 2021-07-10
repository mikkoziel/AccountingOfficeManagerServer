package com.example.AccountingOfficeManagerServer.entity;

import com.example.AccountingOfficeManagerServer.entity.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "work_log")
@Inheritance(strategy = InheritanceType.JOINED)
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int worklog_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="worklog")
    private Employee employee;

    public WorkLog() {
    }


    public WorkLog(int worklog_id, Date date, int duration, Employee employee) {
        this.worklog_id = worklog_id;
        this.date = date;
        this.duration = duration;
        this.employee = employee;
    }

    public int getWorklog_id() {
        return worklog_id;
    }

    public void setWorklog_id(int worklog_id) {
        this.worklog_id = worklog_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
