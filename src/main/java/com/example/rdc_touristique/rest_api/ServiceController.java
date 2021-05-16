package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ServiceDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController  extends AbstratCrudController<ServiceDTO, Integer>{
    public ServiceController(CrudService<ServiceDTO, Integer> service) {
        super(service);
    }

//    @PostMapping("/ville")
//    public List<ServiceDTO> getAllByVille
}
