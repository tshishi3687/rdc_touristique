package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.ContratMisEnLigneDTO;
import com.example.rdc_touristique.business.service.ContratMisEnLigneService;
import com.example.rdc_touristique.business.service.CrudService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contrat")
public class ContratMisEnLigneController extends AbstratCrudController<ContratMisEnLigneDTO, Integer> {
    public ContratMisEnLigneController(CrudService<ContratMisEnLigneDTO,Integer>service){super(service);}

    @GetMapping("/mis_en_ligne")
    public List<ContratMisEnLigneDTO> selonPreuneur() throws Exception {
        return ((ContratMisEnLigneService)service).selonLePrenneur();
    }
}
