package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.Type_serviceDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type")
public class TypeController extends AbstratCrudController<Type_serviceDTO, Integer>{
    public TypeController(CrudService<Type_serviceDTO, Integer> service) {
        super(service);
    }
}
