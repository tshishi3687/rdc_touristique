package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.TypeDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type")
public class TypeController extends AbstratCrudController<TypeDTO, Integer>{
    public TypeController(CrudService<TypeDTO, Integer> service) {
        super(service);
    }
}
