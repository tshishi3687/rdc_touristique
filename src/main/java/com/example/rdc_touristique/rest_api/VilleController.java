package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ville")
public class VilleController extends AbstratCrudController<VilleDTO, Integer>{
    public VilleController(CrudService<VilleDTO, Integer> service) {
        super(service);
    }
}
