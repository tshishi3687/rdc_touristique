package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.InfoBancaireDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.InfoBancaireService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("info_bancaire")
public class InfoBancaireController extends AbstratCrudController<InfoBancaireDTO, Integer> {
    public InfoBancaireController(CrudService<InfoBancaireDTO, Integer> service){super(service);}

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<InfoBancaireDTO> infoBancaire(PersonneSimplifierDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((InfoBancaireService)service).selonPersonne(dto);
    }
}
