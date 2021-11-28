package com.example.rdc_touristique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RdcTouristiqueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RdcTouristiqueApplication.class, args);
		System.out.println("c'est parti :)");
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}
}
