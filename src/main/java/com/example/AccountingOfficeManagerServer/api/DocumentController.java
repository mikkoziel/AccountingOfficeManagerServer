package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.Document;
import com.example.AccountingOfficeManagerServer.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    @GetMapping("")
    public List<Document> list() {
        return documentService.listAllDocument();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Document> get(@PathVariable Integer id) {
        try {
            Document document = documentService.getDocument(id);
            return new ResponseEntity<Document>(document, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Document>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public void add(@RequestBody Document document) {
        documentService.saveDocument(document);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Document document, @PathVariable Integer id) {
        try {
            Document existDocument = documentService.getDocument(id);
//            Document.setId(id);
            documentService.saveDocument(document);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.deleteDocument(id);
    }
}
