package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    @Query(value = "SELECT * FROM client" +
            " JOIN user" +
            " ON client.user_id=user.user_id" +
            " AND client.employee_id=?1" +
            " JOIN company" +
            " ON user.company_id=company.company_id"
            , nativeQuery = true)
    List<Client> findByEmployeeId(Integer user_id);

    @Query(value = "SELECT * FROM client" +
            " JOIN user" +
            " ON client.user_id=user.user_id" +
            " AND user.company_id=?1"
            , nativeQuery = true)
    List<Client> findByCompanyId(Integer company_id);

    @Query(value = "SELECT * FROM client" +
            " JOIN user" +
            " ON client.user_id=user.user_id" +
            " AND client.employee_id IN" +
            " (SELECT user_id from employee" +
            " WHERE admin_id=?1 or user_id=?1)"
            , nativeQuery = true)
    List<Client> findByAdminId(Integer user_id);
}