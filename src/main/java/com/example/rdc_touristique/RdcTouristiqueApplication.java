package com.example.rdc_touristique;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

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

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		// Maximum file size
		factory.setMaxFileSize(DataSize.parse("30960KB")); // KB,MB
		// / Set the total upload data size
		factory.setMaxRequestSize(DataSize.parse("309600KB"));
		return factory.createMultipartConfig() ;
	}
}
