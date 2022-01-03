package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.ReservationDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController extends AbstratCrudController<ReservationDTO, Integer> {
    public ReservationController(CrudService<ReservationDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getAllByUser(@RequestBody PersonneSimpleDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((ReservationService)service).selonLaPer(dto);
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationDTO> getAllByClient(@RequestBody PersonneSimpleDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return ((ReservationService)service).reservationFait(dto);
    }

//    @PostMapping("/date")
//    @ResponseStatus(HttpStatus.OK)
//    public boolean getAllByUser(@RequestBody ReservationDTO dto) throws ReservationFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
//        return ((ReservationService)service).verifDate(dto);
//    }
}
