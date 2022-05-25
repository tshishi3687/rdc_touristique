package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.VilleService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import com.example.rdc_touristique.exeption.VilleFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("ville")
public class VilleController extends AbstratCrudController<VilleDTO, Integer>{
    public VilleController(CrudService<VilleDTO, Integer> service) {
        super(service);
    }


    @PostMapping("/delate")
    @ResponseStatus(HttpStatus.OK)
    public void supprimerVille(@RequestBody int id) throws VilleFoundExeption {
        ((VilleService)service).deleteVille(id);
    }
}
