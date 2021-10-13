package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.DemandeDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.DemandeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("demande")
public class DemandeController extends AbstratCrudController<DemandeDTO, Integer> {
    public DemandeController(CrudService<DemandeDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<DemandeDTO> getAllByUser(@RequestBody PersonneSimpleDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((DemandeService)service).selonLaPer(dto);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    public List<DemandeDTO> getAllByClient(@RequestBody PersonneSimpleDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((DemandeService)service).damandeFaite(dto);
    }

//    @PostMapping("/date")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean getAllByUser(@RequestBody ReservationDTO dto) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
//        return ((ReservationService)service).verifDate(dto);
//    }
}
