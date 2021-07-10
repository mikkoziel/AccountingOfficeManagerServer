package com.example.AccountingOfficeManagerServer.repository;

import com.example.AccountingOfficeManagerServer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserRepository extends JpaRepository<User, Integer>{
    public User loadUserByUsername(String username)
            throws UsernameNotFoundException;
}