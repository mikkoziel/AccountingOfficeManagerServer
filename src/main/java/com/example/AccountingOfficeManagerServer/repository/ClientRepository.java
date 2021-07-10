package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer>{
}