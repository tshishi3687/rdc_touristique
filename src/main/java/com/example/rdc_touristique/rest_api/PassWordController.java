package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.PassWordDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pass_word")
public class PassWordController extends AbstratCrudController<PassWordDTO, Integer>{
    public PassWordController(CrudService<PassWordDTO, Integer> service){super (service);}

}
