package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Calendar;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    public List<Calendar> listAllCalendar() {
        return calendarRepository.findAll();
    }

    public void saveCalendar(Calendar calendar) {
        calendarRepository.save(calendar);
    }

    public Calendar getCalendar(Integer id) {
        return calendarRepository.findById(id).get();
    }

    public void deleteCalendar(Integer id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> listAllCalendarForUser(Integer user_id) {return calendarRepository.findByUserId(user_id);}
}
