package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkLogRepository extends JpaRepository<WorkLog, Integer>{
    @Query(value = "SELECT * FROM work_log WHERE user_id=?1 ORDER BY date DESC;", nativeQuery = true)
    List<WorkLog> findByUserId(Integer user_id);
}