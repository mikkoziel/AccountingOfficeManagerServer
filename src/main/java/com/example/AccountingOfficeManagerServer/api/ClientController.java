package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.service.ClientService;
import com.example.AccountingOfficeManagerServer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping("")
    public List<Client> list() {
        return clientService.listAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> get(@PathVariable Integer id) {
        try {
            Client client = clientService.getClient(id);
            return new ResponseEntity<Client>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Client client) {
        clientService.saveClient(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Client client, @PathVariable Integer id) {
        try {
            Client existAccountingOffice = clientService.getClient(id);
//            user.setId(id);
            clientService.saveClient(client);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }

    @GetMapping("/user/{id}")
    public List<Client> listForUser(@PathVariable Integer id) {
        List<Client> clients = clientService.listAllClientForUser(id);
//        logger.info(clients.toString());
        return clients;
    }
}
