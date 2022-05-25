package com.example.rdc_touristique;

import com.example.rdc_touristique.business.service.ImageModelService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RdcTouristiqueApplication {



	public static void main(String[] args) {
		SpringApplication.run(RdcTouristiqueApplication.class, args);
		System.out.println("=> Service RDC: C'est parti :)");
	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}
}
