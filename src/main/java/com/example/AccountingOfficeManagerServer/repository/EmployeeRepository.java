package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    Employee findByUsername(String username) throws UsernameNotFoundException;

    @Query(value = "SELECT * FROM employee" +
            " JOIN user" +
            " ON employee.user_id=user.user_id" +
            " AND employee.admin_id=?1"
            , nativeQuery = true)
    List<Employee> findByAdmin(Integer admin_id);
}