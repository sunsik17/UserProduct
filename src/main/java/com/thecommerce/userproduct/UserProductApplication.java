package com.thecommerce.userproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UserProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserProductApplication.class, args);
	}

}
