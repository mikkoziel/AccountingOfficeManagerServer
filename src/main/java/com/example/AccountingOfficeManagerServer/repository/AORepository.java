package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.AccountingOffice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AORepository extends JpaRepository<AccountingOffice, Integer>{
}