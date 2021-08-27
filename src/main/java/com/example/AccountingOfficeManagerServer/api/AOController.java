package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.modelpack.RegisterAO;
import com.example.AccountingOfficeManagerServer.service.AOService;
import com.example.AccountingOfficeManagerServer.entity.model.AccountingOffice;
import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.service.UserService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/ao")
public class AOController {
    @Autowired
    AOService aoService;

    private static final Logger logger = LoggerFactory.getLogger(AOController.class);

    @GetMapping("")
    public List<AccountingOffice> list() {
        return aoService.listAllAccountingOffices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountingOffice> get(@PathVariable Integer id) {
        try {
            AccountingOffice accountingOffice = aoService.getAccountingOffice(id);
            return new ResponseEntity<>(accountingOffice, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<Employee>> getEmployees(@PathVariable Integer id) {
        try {
            AccountingOffice accountingOffice = aoService.getAccountingOffice(id);
            List<Employee> employees = new ArrayList<>();
            for (User u : accountingOffice.getUsers()) {
                if (u instanceof Employee)
                    employees.add((Employee)u);
            }
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<AccountingOffice> add(@RequestBody AccountingOffice accountingOffice) {
        AccountingOffice saved_ao = aoService.saveAccountingOffice(accountingOffice);
        return new ResponseEntity<>(saved_ao, HttpStatus.OK);
    }

    @PostMapping("/register")
    public void registerAO(@RequestBody RegisterAO registerAO) {
        logger.info(registerAO.toString());
        aoService.registerAccountingOffice(registerAO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AccountingOffice accountingOffice, @PathVariable Integer id) {
        try {
            AccountingOffice existAccountingOffice = aoService.getAccountingOffice(id);
            existAccountingOffice.setCompany_id(id);
            aoService.saveAccountingOffice(accountingOffice);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        aoService.deleteAccountingOffice(id);
    }
}
