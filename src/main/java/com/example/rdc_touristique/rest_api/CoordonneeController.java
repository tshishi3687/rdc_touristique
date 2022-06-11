package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("coordonnee")
public class CoordonneeController extends AbstratCrudController<CoordonneeDTO, Integer>{
    public CoordonneeController(CrudService<CoordonneeDTO, Integer> service) {
        super(service);
    }
}
