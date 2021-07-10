package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.Client;
import com.example.AccountingOfficeManagerServer.service.ClientService;
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
}