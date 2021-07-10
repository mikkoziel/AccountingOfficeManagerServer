package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
}