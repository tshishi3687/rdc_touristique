package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.PersonneService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("personne")
public class PersonneController extends AbstratCrudController<PersonneSimpleDTO, Integer>{

    public PersonneController(CrudService<PersonneSimpleDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public PersonneSimpleDTO getUser(@RequestBody PersonneSimpleDTO dto)  {
        return ((PersonneService)service).seloguer(dto);
    }
}
