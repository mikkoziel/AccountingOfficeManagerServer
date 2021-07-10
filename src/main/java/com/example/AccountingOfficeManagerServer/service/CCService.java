package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.ClientCompany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CCService {
    @Autowired
    private com.example.AccountingOfficeManagerServer.repository.CCRepository CCRepository;

    public List<ClientCompany> listAllClientCompany() {
        return CCRepository.findAll();
    }

    public void saveClientCompany(ClientCompany clientCompany) {
        CCRepository.save(clientCompany);
    }

    public ClientCompany getClientCompany(Integer id) {
        return CCRepository.findById(id).get();
    }

    public void deleteClientCompany(Integer id) {
        CCRepository.deleteById(id);
    }
}