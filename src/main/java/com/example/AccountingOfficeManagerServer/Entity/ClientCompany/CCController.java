package com.example.AccountingOfficeManagerServer.Entity.ClientCompany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/cc")
public class CCController {
    @Autowired
    CCService CCService;

    @GetMapping("")
    public List<ClientCompany> list() {
        return CCService.listAllClientCompany();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientCompany> get(@PathVariable Integer id) {
        try {
            ClientCompany clientCompany = CCService.getClientCompany(id);
            return new ResponseEntity<ClientCompany>(clientCompany, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ClientCompany>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody ClientCompany clientCompany) {
        CCService.saveClientCompany(clientCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ClientCompany clientCompany, @PathVariable Integer id) {
        try {
            ClientCompany existClientCompany = CCService.getClientCompany(id);
            clientCompany.setCompany_id(id);
            CCService.saveClientCompany(clientCompany);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        CCService.deleteClientCompany(id);
    }
}
