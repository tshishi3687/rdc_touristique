package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ProvinceService;
import com.example.rdc_touristique.business.service.VilleService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("province")
public class ProvinceController extends AbstratCrudController<ProvinceDTO, Integer>{
    public ProvinceController(CrudService<ProvinceDTO, Integer> service) {
        super(service);
    }


}
