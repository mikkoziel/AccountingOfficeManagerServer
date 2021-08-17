package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.service.WorkLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/work-log")
public class WorkLogController {
    @Autowired
    WorkLogService workLogService;
    private static final Logger logger = LoggerFactory.getLogger(WorkLogController.class);

    @GetMapping("")
    public List<WorkLog> list() {
        return workLogService.listAllWorkLog();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkLog> get(@PathVariable Integer id) {
        try {
            WorkLog workLog = workLogService.getWorkLog(id);
            return new ResponseEntity<WorkLog>(workLog, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<WorkLog>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody WorkLog workLog) {
//        logger.info(workLog.toString());
        workLogService.saveWorkLog(workLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody WorkLog workLog, @PathVariable Integer id) {
        try {
            WorkLog existWorkLog = workLogService.getWorkLog(id);
            workLog.setWorklog_id(id);
            workLogService.saveWorkLog(workLog);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        workLogService.deleteWorkLog(id);
    }

    @GetMapping("/user/{id}")
    public List<WorkLog> listForUser(@PathVariable Integer id) {
        return workLogService.listAllWorkLogForUser(id);
    }

}
