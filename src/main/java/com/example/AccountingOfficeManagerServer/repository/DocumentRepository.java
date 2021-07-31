package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.Document;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{
    @Query(value = "SELECT * FROM documents WHERE user_id=?1;", nativeQuery = true)
    List<Document> findByUserId(Integer user_id);

    @Query(value = "SELECT * FROM documents WHERE comapny_id=?1;", nativeQuery = true)
    List<Document> findByCompanyId(Integer company_id);
}