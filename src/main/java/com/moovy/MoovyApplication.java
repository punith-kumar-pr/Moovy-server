package com.moovy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MoovyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoovyApplication.class, args);
	}

	@GetMapping
	public String run() {
		return "Moovy Application";
	}
}
