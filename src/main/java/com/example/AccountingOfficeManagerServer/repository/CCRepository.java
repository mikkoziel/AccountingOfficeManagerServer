package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CCRepository extends JpaRepository<ClientCompany, Integer>{
}