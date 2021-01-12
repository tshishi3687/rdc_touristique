package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.rest_api.AbstratCrudController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("bien")
public class BienController extends AbstratCrudController<BienDTO, Integer> {

    public BienController(CrudService<BienDTO, Integer> service) {
        super(service);
    }
}
