package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.ContactUserDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.ContactUserService;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("contact_user")
public class ContactUserController extends AbstratCrudController<ContactUserDTO, Integer> {
    public ContactUserController(CrudService<ContactUserDTO, Integer> service){super(service);}

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ContactUserDTO adressPersonne(PersonneSimplifierDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((ContactUserService)service).selonPersonne(dto);
    }
}
