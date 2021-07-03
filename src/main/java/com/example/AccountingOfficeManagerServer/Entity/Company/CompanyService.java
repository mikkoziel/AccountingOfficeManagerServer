package com.example.AccountingOfficeManagerServer.Entity.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> listAllCompany() {
        return companyRepository.findAll();
    }

    public void saveCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompany(Integer id) {
        return companyRepository.findById(id).get();
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
