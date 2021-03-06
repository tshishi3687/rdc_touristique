package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.Type_bienDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("type_bien")
public class Type_bienController extends AbstratCrudController<Type_bienDTO, Integer>{
    public Type_bienController(CrudService<Type_bienDTO, Integer> service) {
        super(service);
    }
}
