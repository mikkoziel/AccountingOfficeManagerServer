package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.Employee;
import com.example.AccountingOfficeManagerServer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> listAllUser() {
        return employeeRepository.findAll();
    }

    public void saveUser(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getUser(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        employeeRepository.deleteById(id);
    }
}
