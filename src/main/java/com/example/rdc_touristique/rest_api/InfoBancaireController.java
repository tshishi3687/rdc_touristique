package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.InfoBancaireService;
import com.example.rdc_touristique.exeption.PersonneInfoExisteExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("info_bancaire")
public class InfoBancaireController extends AbstratCrudController<InfoBancaireDTO, Integer> {
    public InfoBancaireController(CrudService<InfoBancaireDTO, Integer> service){super(service);}

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public InfoBancaireDTO infoBancaire(@RequestBody PersonneSimplifierDTO dto) throws PersonneInfoExisteExeption {
        return ((InfoBancaireService)service).selonPersonne(dto);
    }
}
