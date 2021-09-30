package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.entity.modelpack.AssignClientToEmployee;
import com.example.AccountingOfficeManagerServer.entity.modelpack.ClientInfo;
import com.example.AccountingOfficeManagerServer.service.ClientService;
import com.example.AccountingOfficeManagerServer.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
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
            return new ResponseEntity<>(client, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Client> add(@RequestBody Client client) {
        try {
            Client saved_client = clientService.saveClient(client);
            return new ResponseEntity<>(saved_client, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
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
        return clientService.listAllClientForUser(id);
    }

    @GetMapping("/cc/{id}")
    public List<Client> listForCC(@PathVariable Integer id) {
        return clientService.listAllClientForCC(id);
    }

    @GetMapping("/admin/{id}")
    public List<Client> listForAdmin(@PathVariable Integer id) {
        return clientService.listAllClientForAdmin(id);
    }

    @PostMapping("/employee")
    public void assignEmployee(@RequestBody AssignClientToEmployee assign) {
        clientService.assignEmployee(assign);
    }

    @GetMapping("/client-info/{id}")
    public ClientInfo getClientInfo(@PathVariable Integer id) {
        ClientInfo info = clientService.getClientInfo(id);
        logger.info(info.toString());
        return info;
    }

}
