package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public void saveClient(Client accountingOffice) {
        clientRepository.save(accountingOffice);
    }

    public Client getClient(Integer id) {
        return clientRepository.findById(id).get();
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<Client> listAllClientForUser(Integer user_id) {return clientRepository.findByEmployeeId(user_id);}

}
