package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.PaysDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pays")
public class PaysControlleur extends AbstratCrudController<PaysDTO, Integer>{
    public PaysControlleur (CrudService<PaysDTO, Integer> service){super(service);}
}
