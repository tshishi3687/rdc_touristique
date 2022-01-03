package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienMisEnLigneDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.service.BienMisEnLigneService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.PersonneSimplifierFoundExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bienmisenligne")
public class BienMisEnLigneController extends AbstratCrudController<BienMisEnLigneDTO, Integer>{
    public BienMisEnLigneController(CrudService<BienMisEnLigneDTO, Integer> service) { super(service);}

    // vue du bailleur de la list des bien_mis_en_ligne => pouvant servir aussi à voir les contrats liéés.
    @PostMapping("/bailler")
    @ResponseStatus(HttpStatus.OK)
    public List<BienMisEnLigneDTO> readByBailleur(@RequestBody PersonneSimpleDTO bailleur) throws PersonneSimplifierFoundExeption {
        // je le renvois vers BienMisEnLigneService pour avoir la réponse de la fonction allByContrat_bailleur.
        return ((BienMisEnLigneService)service).allByContrat_bailleur(bailleur);
    }

    // vue du preneur de la list des bien_mis_en_ligne => pouvant servir aussi à voir les contrats liéés.
    @PostMapping("/preneur")
    @ResponseStatus(HttpStatus.OK)
    public List<BienMisEnLigneDTO> readByPreneur(@RequestBody PersonneSimpleDTO preneur) throws PersonneSimplifierFoundExeption {
        // je le roivois vers BienMisEnLigneService pour avoir la réponse de la fonction allByContrat_preneur
        return ((BienMisEnLigneService)service).allByContrat_preneur(preneur);
    }
}
