package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.AdressUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.AdressUserService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.AdressUserExisteExeption;
import com.example.rdc_touristique.exeption.PersonneInfoExisteExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("adress_user")
public class AdressUserController extends AbstratCrudController<AdressUserDTO, Integer>{
    public AdressUserController(CrudService<AdressUserDTO, Integer> service){super(service);}

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public AdressUserDTO adressPersonne(@RequestBody PersonneSimplifierDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException, AdressUserExisteExeption, PersonneInfoExisteExeption {
        return ((AdressUserService)service).selonPersonne(dto);
    }
}
