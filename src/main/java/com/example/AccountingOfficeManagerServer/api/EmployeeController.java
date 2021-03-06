package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.service.EmployeeService;
import com.example.AccountingOfficeManagerServer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping("")
    public List<Employee> list() {
        return employeeService.listAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable Integer id) {
        try {
            Employee employee = employeeService.getUser(id);
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Employee> add(@RequestBody Employee employee) {
//        logger.info(employee.toString());
        Employee saved_employee = employeeService.saveUser(employee);
        return new ResponseEntity<>(saved_employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Employee employee, @PathVariable Integer id) {
        try {
            Employee existEmployee = employeeService.getUser(id);
            employee.setUser_id(id);
            employeeService.saveUser(employee);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        employeeService.deleteUser(id);
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Employee>> getByAdmin(@PathVariable Integer id) {
        try {
            List<Employee> employees = employeeService.findByAdmin(id);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ccompany/{id}")
    public ResponseEntity<List<Employee>> getByClientCompany(@PathVariable Integer id) {
        try {
            List<Employee> employees = employeeService.findByClientCompany(id);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
