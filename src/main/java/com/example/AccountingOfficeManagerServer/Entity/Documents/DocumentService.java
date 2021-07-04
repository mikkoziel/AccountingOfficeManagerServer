package com.example.AccountingOfficeManagerServer.Entity.Documents;

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
