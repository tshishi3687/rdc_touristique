package com.example.rdc_touristique;


import com.example.rdc_touristique.business.dto.BienVuSimplifierDTO;
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
	private Mapper<BienVuSimplifierDTO, Bien> bienVuDTOBienMapper;

	@Test
	void contextLoads() {
		List<BienVuSimplifierDTO> list = bienRepository.findByModeActiveTrueOrderByIdDesc().stream()
				.map(bienVuDTOBienMapper::toDTO)
				.collect(Collectors.toList());
		System.out.println(list.size());
	}

}
