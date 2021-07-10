package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
}