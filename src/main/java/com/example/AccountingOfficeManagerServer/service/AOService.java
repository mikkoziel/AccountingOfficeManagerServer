package com.example.AccountingOfficeManagerServer.service;

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

    public List<AccountingOffice> listAllAccountingOffices() {
        return aoRepository.findAll();
    }

    public void saveAccountingOffice(AccountingOffice accountingOffice) {
        aoRepository.save(accountingOffice);
    }

    public AccountingOffice getAccountingOffice(Integer id) {
        return aoRepository.findById(id).get();
    }

    public void deleteAccountingOffice(Integer id) {
        aoRepository.deleteById(id);
    }
}
