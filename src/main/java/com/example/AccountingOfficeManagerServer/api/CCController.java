package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.model.ClientCompany;
import com.example.AccountingOfficeManagerServer.entity.modelpack.RegisterCC;
import com.example.AccountingOfficeManagerServer.service.CCService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CCController.class);

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

    @PostMapping("/register")
    public void register(@RequestBody RegisterCC registerCC) {
        CCService.registerClientCompany(registerCC);
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

    @GetMapping("/ao/{id}")
    public ResponseEntity<List<ClientCompany>> getByAO(@PathVariable Integer id) {
        try {
            List<ClientCompany> ccs = CCService.findByAO(id);
            return new ResponseEntity<>(ccs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
