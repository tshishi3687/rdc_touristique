package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.Lien_photoDTO;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("lien_photo")
public class Lien_photoController extends AbstratCrudController<Lien_photoDTO, Integer>{
    public Lien_photoController(CrudService<Lien_photoDTO, Integer> service) {
        super(service);
    }
}
