package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.Calendar;
import com.example.AccountingOfficeManagerServer.entity.model.User;

import java.util.List;

public class AddCalendarEvent {
    private Calendar calendar;
    private List<User> users;

    public AddCalendarEvent() {
    }

    public AddCalendarEvent(Calendar calendar, List<User> users) {
        this.calendar = calendar;
        this.users = users;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
