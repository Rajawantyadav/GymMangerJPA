package com.gymmanager.newgymmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class NewgymmanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewgymmanagerApplication.class, args);
	}

}
