package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.Calendar;
import com.example.AccountingOfficeManagerServer.service.CalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;
    private static final Logger logger = LoggerFactory.getLogger(CalendarService.class);

    @GetMapping("")
    public List<Calendar> list() {
        return calendarService.listAllCalendar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Calendar> get(@PathVariable Integer id) {
        try {
            Calendar calendar = calendarService.getCalendar(id);
            return new ResponseEntity<Calendar>(calendar, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Calendar>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Calendar calendar) {
        calendarService.saveCalendar(calendar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Calendar calendar, @PathVariable Integer id) {
        try {
            Calendar existCalendar = calendarService.getCalendar(id);
            calendar.setCalendar_id(id);
            calendarService.saveCalendar(calendar);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        calendarService.deleteCalendar(id);
    }
}
