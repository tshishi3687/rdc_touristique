package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.BienService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.BienExisteExeption;
import com.example.rdc_touristique.exeption.BienFoundExeption;
import com.example.rdc_touristique.exeption.PersonneSimpleExisteExeption;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping("bien")
public class BienController extends AbstratCrudController<BienVuDTO, Integer> {

    public BienController(CrudService<BienVuDTO, Integer> service) {
        super(service);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<BienVuDTO> getAllByUser(@RequestBody PersonneSimplifierDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException, BienFoundExeption {
        return ((BienService)service).selonLaPersonne();
    }

    @PostMapping("/creatt")
    @ResponseStatus(HttpStatus.OK)
    public int creatBient(@RequestBody BienDTO bienDTO) throws Exception {
        return ((BienService)service).creatKey(bienDTO);
    }

    @GetMapping("/bien_likes")
    public int count_like(@RequestBody BienVuDTO toDTO) throws Exception {
        return 0;

    }

    @PostMapping("/deletebien")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBien(@RequestBody BienVuDTO bienDTO) throws BienFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        ((BienService)service).deleteBien(bienDTO);
    }

    @PostMapping("/acti")
    @ResponseStatus(HttpStatus.OK)
    public void activationBien(@RequestBody BienVuDTO bienDTO) throws BienFoundExeption, PersonneSimpleExisteExeption {
        ((BienService)service).activationBien(bienDTO);
    }

    @PostMapping("/env_mail")
    @ResponseStatus(HttpStatus.OK)
    public void envoiMail(@RequestBody BienVuDTO bienDTO) throws BienFoundExeption, PersonneSimpleExisteExeption, MessagingException, BienExisteExeption, NoSuchAlgorithmException {
        ((BienService)service).maildeconfirmationBienMisEnLigne(bienDTO);
    }

    @PostMapping("/envo_mail")
    @ResponseStatus(HttpStatus.OK)
    public long envoiMailReservation(@RequestBody ReservationBienDTO reservationBienDTO) throws BienFoundExeption, PersonneSimpleExisteExeption, MessagingException, BienExisteExeption, NoSuchAlgorithmException {
       return ((BienService)service).maildeconfirmationBienReserve(reservationBienDTO);
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.OK)
    public boolean reservation(@RequestBody ReservationBienDTO reservationBienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
       return ((BienService)service).reservationBien(reservationBienDTO);
    }



}
