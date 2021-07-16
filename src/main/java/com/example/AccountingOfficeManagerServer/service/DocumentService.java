package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.entity.model.Document;
import com.example.AccountingOfficeManagerServer.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;
    public List<Document> listAllDocument() {
        return documentRepository.findAll();
    }

    public void saveDocument(Document document) {
        documentRepository.save(document);
    }

    public Document getDocument(Integer id) {
        return documentRepository.findById(id).get();
    }

    public void deleteDocument(Integer id) {
        documentRepository.deleteById(id);
    }
}
