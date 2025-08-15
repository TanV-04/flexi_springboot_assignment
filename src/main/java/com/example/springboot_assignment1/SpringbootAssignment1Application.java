package com.example.springboot_assignment1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringbootAssignment1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAssignment1Application.class, args);
	}

}
