package com.example.AccountingOfficeManagerServer.entity.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "calendar")
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="calendar_id")
public class Calendar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int calendar_id;

    @ManyToMany(mappedBy = "calendars")
    private List<User> users = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_date;
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_date;

    String title;
    Boolean all_day;

    public Calendar() {
    }

    public Calendar(int calendar_id, List<User> users, Date start_date, Date end_date, String title, Boolean all_day) {
        this.calendar_id = calendar_id;
        this.users = users;
        this.start_date = start_date;
        this.end_date = end_date;
        this.title = title;
        this.all_day = all_day;
    }

    public int getCalendar_id() {
        return calendar_id;
    }

    public void setCalendar_id(int calendar_id) {
        this.calendar_id = calendar_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getAll_day() {
        return all_day;
    }

    public void setAll_day(Boolean all_day) {
        this.all_day = all_day;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "\"calendar_id\": " + calendar_id +
                ", \"start_date\": \"" + start_date.toString() +
                "\", \"end_date\": \"" + end_date.toString() +
                "\", \"title\": \"" + title + '\"' +
                ", \"all_day\": " + all_day +
                '}';
    }
}
