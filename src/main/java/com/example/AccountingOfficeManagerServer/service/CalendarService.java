package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.api.CalendarController;
import com.example.AccountingOfficeManagerServer.entity.model.Calendar;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.entity.modelpack.AddCalendarEvent;
import com.example.AccountingOfficeManagerServer.repository.CalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    public List<Calendar> listAllCalendar() {
        return calendarRepository.findAll();
    }

    public Calendar saveCalendar(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public Calendar getCalendar(Integer id) {
        return calendarRepository.findById(id).get();
    }

    public void deleteCalendar(Integer id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> listAllCalendarForUser(Integer user_id) {return calendarRepository.findByUserId(user_id);}

    public void saveCalendarEvent(AddCalendarEvent calendarEvent){
        Calendar calendar = calendarEvent.getCalendar();
        calendar = this.saveCalendar(calendar);
        for(User user: calendarEvent.getUsers()){
            User user_tmp = this.userService.loadUserByUsername(user.getUsername());
            user_tmp.addCalendar(calendar);
        }
    }

}
