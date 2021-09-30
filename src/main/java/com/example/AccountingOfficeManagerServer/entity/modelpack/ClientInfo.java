package com.example.AccountingOfficeManagerServer.entity.modelpack;

import com.example.AccountingOfficeManagerServer.entity.model.Client;
import com.example.AccountingOfficeManagerServer.entity.model.Document;

import java.util.List;

public class ClientInfo {
    private Client client;
    private List<Document> documents;

    public ClientInfo(){
    }

    public ClientInfo(Client client, List<Document> documents) {
        this.client = client;
        this.documents = documents;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        return "{" +
                "\"client\": " + client +
                ", \"documents\": " + documents +
                '}';
    }
}
