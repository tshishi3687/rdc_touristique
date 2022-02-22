package com.example.rdc_touristique;

import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RdcTouristiqueApplication {



	public static void main(String[] args) {
		SpringApplication.run(RdcTouristiqueApplication.class, args);
		System.out.println("=> Service RDC: C'est parti :)");
	}

	public void testBien(){

	}

	@Bean
	BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}
}
