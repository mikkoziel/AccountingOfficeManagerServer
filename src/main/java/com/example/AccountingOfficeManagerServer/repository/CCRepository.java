package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.ClientCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CCRepository extends JpaRepository<ClientCompany, Integer>{
    @Query(value = "SELECT * FROM client_company" +
            " JOIN company" +
            " ON company.company_id=client_company.company_id" +
            " AND client_company.ao_id=?1",
            nativeQuery = true)
    List<ClientCompany> findByAO(Integer ao_id);
}