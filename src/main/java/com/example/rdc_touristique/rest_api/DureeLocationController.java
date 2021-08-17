package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.DureeLocationDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dureelocation")
public class DureeLocationController extends AbstratCrudController<DureeLocationDTO, Integer> {
    public DureeLocationController(CrudService<DureeLocationDTO, Integer> service) {
        super(service);
    }

}
