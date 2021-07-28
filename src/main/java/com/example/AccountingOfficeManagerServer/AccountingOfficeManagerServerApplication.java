package com.example.AccountingOfficeManagerServer;

import com.example.AccountingOfficeManagerServer.entity.configuration.StorageProperties;
import com.example.AccountingOfficeManagerServer.service.DocumentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class AccountingOfficeManagerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountingOfficeManagerServerApplication.class, args);
	}

	@Bean
	CommandLineRunner init(DocumentService storageService) {
		return (args) -> {
//			storageService.deleteAll();
			storageService.init();
		};
	}
}
