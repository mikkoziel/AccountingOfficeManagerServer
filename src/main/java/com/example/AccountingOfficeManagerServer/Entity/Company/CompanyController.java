package com.example.AccountingOfficeManagerServer.Entity.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService comapnyService;

    @GetMapping("")
    public List<Company> list() {
        return comapnyService.listAllCompany();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable Integer id) {
        try {
            Company company = comapnyService.getCompany(id);
            return new ResponseEntity<Company>(company, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Company company) {
        comapnyService.saveCompany(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Integer id) {
        try {
            Company existCompany = comapnyService.getCompany(id);
            company.setCompany_id(id);
            comapnyService.saveCompany(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        comapnyService.deleteCompany(id);
    }
}
