package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.Company;
import com.example.AccountingOfficeManagerServer.service.CCService;
import com.example.AccountingOfficeManagerServer.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    CompanyService companyService;

    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @GetMapping("")
    public List<Company> list() {
        return companyService.listAllCompany();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> get(@PathVariable Integer id) {
        try {
            Company company = companyService.getCompany(id);
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Company company) {
        companyService.saveCompany(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Company company, @PathVariable Integer id) {
        try {
            Company existCompany = companyService.getCompany(id);
            company.setCompany_id(id);
            companyService.saveCompany(company);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        companyService.deleteCompany(id);
    }
}
