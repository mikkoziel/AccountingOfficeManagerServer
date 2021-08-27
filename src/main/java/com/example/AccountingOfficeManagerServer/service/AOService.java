package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.entity.modelpack.RegisterAO;
import com.example.AccountingOfficeManagerServer.repository.AORepository;
import com.example.AccountingOfficeManagerServer.entity.model.AccountingOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AOService {
    @Autowired
    private AORepository aoRepository;
    @Autowired
    private EmployeeService employeeService;

    public List<AccountingOffice> listAllAccountingOffices() {
        return aoRepository.findAll();
    }

    public AccountingOffice saveAccountingOffice(AccountingOffice accountingOffice) {
        return aoRepository.save(accountingOffice);
    }

    public AccountingOffice getAccountingOffice(Integer id) {
        return aoRepository.findById(id).get();
    }

    public void deleteAccountingOffice(Integer id) {
        aoRepository.deleteById(id);
    }

    public void registerAccountingOffice(RegisterAO registerAO){
        AccountingOffice save_ao = this.saveAccountingOffice(registerAO.getAo());
        Employee employee = registerAO.getEmployee();
        employee.setCompany(save_ao);
        this.employeeService.saveUser(employee);
    }
}
