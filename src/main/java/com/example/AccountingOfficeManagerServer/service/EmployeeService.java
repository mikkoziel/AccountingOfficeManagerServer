package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public List<Employee> listAllUser() {
        return employeeRepository.findAll();
    }

    public Employee saveUser(Employee employee) {
        if (employeeRepository.findByUsername(employee.getUsername()) != null) {
            throw new ValidationException("Username exists!");
        }
        if (employee.getCompany() == null){
//            logger.info("tutaj");
            User admin = this.getUser(employee.getAdmin().getUser_id());
            employee.setCompany(admin.getCompany());
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()).replace("{bcrypt}",""));
        return employeeRepository.save(employee);
    }

    public Employee getUser(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public void deleteUser(Integer id) {
        employeeRepository.deleteById(id);
    }

    public List<Employee> findByAdmin( Integer id) { return employeeRepository.findByAdmin(id); }

    public List<Employee> findByClientCompany(Integer id) { return employeeRepository.findByClientCompany(id); }

}
