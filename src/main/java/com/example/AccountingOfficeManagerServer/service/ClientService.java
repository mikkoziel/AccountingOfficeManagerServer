package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.Employee;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.entity.modelpack.AssignClientToEmployee;
import com.example.AccountingOfficeManagerServer.repository.ClientRepository;
import com.example.AccountingOfficeManagerServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.List;

@Service
@Transactional
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeService employeeService;

    public ClientService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(Client client) {
        if (userRepository.findByUsername(client.getUsername()) != null) {
            throw new ValidationException("Username exists!");
        }
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    public Client getClient(Integer id) {
        return clientRepository.findById(id).get();
    }

    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    public List<Client> listAllClientForUser(Integer user_id) {return clientRepository.findByEmployeeId(user_id);}

    public List<Client> listAllClientForCC(Integer company_id) {return clientRepository.findByCompanyId(company_id);}

    public List<Client> listAllClientForAdmin(Integer user_id) {return clientRepository.findByAdminId(user_id);}

    public void assignEmployee(AssignClientToEmployee assign){
        Employee employee = this.employeeService.getUser(assign.getEmployee_id());
        Client client = this.getClient(assign.getClient_id());
        client.setEmployee(employee);
    }

}
