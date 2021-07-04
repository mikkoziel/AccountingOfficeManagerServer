package com.example.AccountingOfficeManagerServer.Entity.AccountingOffice;

import com.example.AccountingOfficeManagerServer.Entity.Employee.Employee;
import com.example.AccountingOfficeManagerServer.Entity.User.User;
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

    @GetMapping("")
    public List<AccountingOffice> list() {
        return aoService.listAllAccountingOffices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountingOffice> get(@PathVariable Integer id) {
        try {
            AccountingOffice accountingOffice = aoService.getAccountingOffice(id);
            return new ResponseEntity<AccountingOffice>(accountingOffice, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<AccountingOffice>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody AccountingOffice accountingOffice) {
        aoService.saveAccountingOffice(accountingOffice);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody AccountingOffice accountingOffice, @PathVariable Integer id) {
        try {
            AccountingOffice existAccountingOffice = aoService.getAccountingOffice(id);
//            user.setId(id);
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
