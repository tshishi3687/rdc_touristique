package com.example.rdc_touristique;

import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class RdcTouristiqueApplicationTests {


	@Autowired
	private BienRepository bienRepository;
	@Autowired
	private Mapper<BienVuDTO, Bien> bienVuDTOBienMapper;

	@Test
	void contextLoads() {
		List<BienVuDTO> list = bienRepository.findByModeActiveOrderByIdDesc().stream()
				.map(bienVuDTOBienMapper::toDTO)
				.collect(Collectors.toList());
		System.out.println(list);
	}

}
