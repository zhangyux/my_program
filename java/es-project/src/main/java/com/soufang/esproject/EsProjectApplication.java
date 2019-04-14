package com.soufang.esproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EntityScan("com.soufang.esproject.entity")
public class EsProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EsProjectApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}

