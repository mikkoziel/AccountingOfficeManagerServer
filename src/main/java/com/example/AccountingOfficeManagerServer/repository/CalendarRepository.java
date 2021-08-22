package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.model.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {
    @Query(value = "SELECT * FROM calendar" +
            " JOIN calendar_user" +
            " ON calendar.calendar_id=calendar_user.calendar_id" +
            " AND calendar_user.user_id=?1",
            nativeQuery = true)
    List<Calendar> findByUserId(Integer user_id);
}
