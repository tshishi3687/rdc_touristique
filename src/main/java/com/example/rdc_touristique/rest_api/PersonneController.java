package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("personne")
public class PersonneController extends AbstratCrudController<PersonneSimpleDTO, Integer>{
    public PersonneController(CrudService<PersonneSimpleDTO, Integer> service) {
        super(service);
    }
}
