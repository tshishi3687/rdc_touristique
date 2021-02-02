package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.BienService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.rest_api.AbstratCrudController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bien")
public class BienController extends AbstratCrudController<BienDTO, Integer> {

    public BienController(CrudService<BienDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<BienDTO> getAllByUser(@RequestBody PersonneSimplifierDTO dto){
        return ((BienService)service).selonLaPersonne(dto);
    }

}
