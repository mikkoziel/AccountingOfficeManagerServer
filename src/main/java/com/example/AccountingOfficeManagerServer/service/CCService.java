package com.example.AccountingOfficeManagerServer.service;

import com.example.AccountingOfficeManagerServer.api.CCController;
import com.example.AccountingOfficeManagerServer.entity.model.AccountingOffice;
import com.example.AccountingOfficeManagerServer.entity.model.ClientCompany;
import com.example.AccountingOfficeManagerServer.entity.model.User;
import com.example.AccountingOfficeManagerServer.entity.modelpack.RegisterCC;
import com.example.AccountingOfficeManagerServer.repository.CCRepository;
import com.example.AccountingOfficeManagerServer.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CCService {
    @Autowired
    private CCRepository CCRepository;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(CCService.class);

    public List<ClientCompany> listAllClientCompany() {
        return CCRepository.findAll();
    }

    public void saveClientCompany(ClientCompany clientCompany) {
        CCRepository.save(clientCompany);
    }

    public ClientCompany getClientCompany(Integer id) {
        return CCRepository.findById(id).get();
    }

    public void deleteClientCompany(Integer id) {
        CCRepository.deleteById(id);
    }

    public List<ClientCompany> findByAO(Integer id) {
        User user = this.userRepository.getById(id);
        return CCRepository.findByAO(user.getCompany().getCompany_id());
    }

    public void registerClientCompany(RegisterCC registerCC){
        User user = this.userRepository.getById(registerCC.getId());
        ClientCompany cc = registerCC.getCc();
        cc.setAccounting_office((AccountingOffice) user.getCompany());
        this.saveClientCompany(cc);
    }

}
