package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.DureeOnLineDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dureelocation")
public class DureeLocationController extends AbstratCrudController<DureeOnLineDTO, Integer> {
    public DureeLocationController(CrudService<DureeOnLineDTO, Integer> service) {
        super(service);
    }

}
