package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("province")
public class ProvinceController extends AbstratCrudController<ProvinceDTO, Integer>{
    public ProvinceController(CrudService<ProvinceDTO, Integer> service) {
        super(service);
    }
}
