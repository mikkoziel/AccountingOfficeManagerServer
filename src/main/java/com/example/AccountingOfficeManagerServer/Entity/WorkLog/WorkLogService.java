package com.example.AccountingOfficeManagerServer.Entity.WorkLog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkLogService {
    @Autowired
    private WorkLogRepository workLogRepository;
    public List<WorkLog> listAllWorkLog() {
        return workLogRepository.findAll();
    }

    public void saveWorkLog(WorkLog workLog) {
        workLogRepository.save(workLog);
    }

    public WorkLog getWorkLog(Integer id) {
        return workLogRepository.findById(id).get();
    }

    public void deleteWorkLog(Integer id) {
        workLogRepository.deleteById(id);
    }
}
