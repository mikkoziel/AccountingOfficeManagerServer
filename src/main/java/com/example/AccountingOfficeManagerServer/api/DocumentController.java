package com.example.AccountingOfficeManagerServer.api;

import com.example.AccountingOfficeManagerServer.entity.exception.StorageFileNotFoundException;
import com.example.AccountingOfficeManagerServer.entity.model.Document;
import com.example.AccountingOfficeManagerServer.entity.model.WorkLog;
import com.example.AccountingOfficeManagerServer.service.DocumentService;
import com.example.AccountingOfficeManagerServer.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/document")
public class DocumentController {
    @Autowired
    DocumentService documentService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @GetMapping("")
    public List<Document> list() {
        return documentService.listAllDocument();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getById(@PathVariable Integer id) {
        try {
            Document document = documentService.getDocument(id);
            Resource file = documentService.loadAsResource(document.getPath());
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + file.getFilename() + "\"").body(file);
//            return new ResponseEntity<Resource>(document, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Resource>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/fn/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getByFileName(@PathVariable String filename) {
        Resource file = documentService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(value="/",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void add(@RequestParam("file") MultipartFile file,
                    @RequestParam("document") String document_data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Document document = objectMapper.readValue(document_data, Document.class);
            String path = documentService.store(file, document);
            document.setPath(path);
            documentService.saveDocument(document);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        };
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Document document, @PathVariable Integer id) {
        try {
            Document existDocument = documentService.getDocument(id);
            document.setDocument_id(id);
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

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public List<Document> listForUser(@PathVariable Integer id) {
        return documentService.listAllDocumentsForUser(id);
    }

    @GetMapping("/company/{id}")
    public List<Document> listForCompany(@PathVariable Integer id) {
        return documentService.listAllDocumentsForCompany(id);
    }
}
