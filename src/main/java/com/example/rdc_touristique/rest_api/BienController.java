package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.LikeBien;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.BienService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.BienExisteExeption;
import com.example.rdc_touristique.exeption.BienFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("bien")
public class BienController extends AbstratCrudController<BienDTO, Integer> {

    public BienController(CrudService<BienDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<BienDTO> getAllByUser(@RequestBody PersonneSimplifierDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((BienService)service).selonLaPersonne(dto);
    }

    @PostMapping("/creatt")
    @ResponseStatus(HttpStatus.OK)
    public int creatBient(@RequestBody BienDTO bienDTO) throws BienExisteExeption, NoSuchAlgorithmException, InvalidKeySpecException, InterruptedException {
        return ((BienService)service).creatKey(bienDTO);
    }

    @GetMapping("/bien_likes")
    public int count_like(@RequestBody BienDTO toDTO) throws Exception {
        System.out.println(toDTO);
        return 0;

    }

    @PostMapping("/deletebien")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBien(@RequestBody BienDTO bienDTO) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        ((BienService)service).deleteBien(bienDTO);
    }
}
