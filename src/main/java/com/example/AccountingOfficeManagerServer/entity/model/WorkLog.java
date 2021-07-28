package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "work_log")
//@Inheritance(strategy = InheritanceType.JOINED)
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="worklog_id")
public class WorkLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int worklog_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int duration;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
//    @JsonBackReference(value="worklog")
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

    @Override
    public String toString() {
        return "WorkLog{" +
                "\"worklog_id\": " + worklog_id +
                ", \"date\": " + date +
                ", \"duration\": " + duration +
                ", \"employee\": " + employee +
                '}';
    }
}
