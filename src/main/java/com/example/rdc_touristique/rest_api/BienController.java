package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.BienService;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.BienExisteExeption;
import com.example.rdc_touristique.exeption.BienFoundExeption;
import com.example.rdc_touristique.exeption.ContratLocationFoundExeption;
import com.example.rdc_touristique.exeption.PersonneSimpleExisteExeption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
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
    public List<BienVuSimplifierDTO> getAllByUser(@RequestBody PersonneSimplifierDTO dto) {
        return ((BienService)service).selonLaPersonne();
    }

    @PostMapping("/readOneBien")
    @ResponseStatus(HttpStatus.OK)
    public BienVuSimplifierDTO readOneBien(@RequestBody int idBien) throws BienFoundExeption {
        return ((BienService)service).readOneBien(idBien);
    }

    @PostMapping("/creatt")
    @ResponseStatus(HttpStatus.OK)
    public int creatBient(@RequestBody BienDTO bienDTO) throws Exception {
        return ((BienService)service).creatKey(bienDTO);
    }

    @GetMapping("/bien_likes")
    public int count_like(@RequestBody BienVuDTO toDTO) {
        return 0;

    }

    @PostMapping("/deletebien")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBien(@RequestBody BienVuDTO bienDTO) throws Exception {
        ((BienService)service).deleteBien(bienDTO);
    }

    @PostMapping("/acti")
    @ResponseStatus(HttpStatus.OK)
    public void activationBien(@RequestBody BienVuDTO bienDTO) throws BienFoundExeption, PersonneSimpleExisteExeption {
        ((BienService)service).activationBien(bienDTO);
    }

    @PostMapping("/annul_cmel")
    @ResponseStatus(HttpStatus.OK)
    public void annulationCMEL(@RequestBody PayPalDTO payPalDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        ((BienService)service).annulationCMEL(payPalDTO);
    }


    @PostMapping("/env_mail")
    @ResponseStatus(HttpStatus.OK)
    public void envoiMail(@RequestBody BienVuDTO bienDTO) throws MessagingException, BienExisteExeption, NoSuchAlgorithmException {
        ((BienService)service).maildeconfirmationBienMisEnLigne(bienDTO);
    }

    @PostMapping("/envo_mail")
    @ResponseStatus(HttpStatus.OK)
    public long envoiMailReservation(@RequestBody ReservationBienDTO reservationBienDTO) throws MessagingException, BienExisteExeption, NoSuchAlgorithmException {
       return ((BienService)service).maildeconfirmationBienReserve(reservationBienDTO);
    }

    @PostMapping("/reservation")
    @ResponseStatus(HttpStatus.OK)
    public int reservation(@RequestBody PayPalRDTO payPalDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, MessagingException, ContratLocationFoundExeption {
        return ((BienService) service).reservationBien(payPalDTO);
    }

    @PostMapping("/details")
    @ResponseStatus(HttpStatus.OK)
    public void ajoutDetails(@RequestBody DetailesDTO detailesDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        ((BienService) service).ajoutDetails(detailesDTO);
    }

    @PostMapping("/dispo")
    @ResponseStatus(HttpStatus.OK)
    public boolean isDisponible(@RequestBody ReservationBienDTO DTO) {
        return ((BienService) service).isDisponible(DTO);
    }

    @GetMapping("/reservations")
    public List<ContratLocationDTO> voirReservations() {
        return ((BienService)service).voirReservation();
    }


    @GetMapping("/allBiens")
    public TryListAllBiens tousBiens(@Valid TryListAllBiens tryListAllBiens){
        return ((BienService)service).tousBiens(tryListAllBiens);
    }

    @PostMapping("/infoBien")
    @ResponseStatus(HttpStatus.OK)
    public BienVuDTO infoBien(@RequestBody int idBien){
        return ((BienService)service).infoBien(idBien);
    }

}
