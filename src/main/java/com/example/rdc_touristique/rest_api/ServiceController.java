package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.ServiceDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("service")
public class ServiceController  extends AbstratCrudController<ServiceDTO, Integer>{
    public ServiceController(CrudService<ServiceDTO, Integer> service) {
        super(service);
    }
}
