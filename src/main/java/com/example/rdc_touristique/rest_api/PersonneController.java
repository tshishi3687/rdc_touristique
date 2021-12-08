package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.PersonneService;
import com.example.rdc_touristique.exeption.PersonneSimpleExisteExeption;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@RestController
@RequestMapping("personne")
public class PersonneController extends AbstratCrudController<PersonneSimpleDTO, Integer>{



    public PersonneController(CrudService<PersonneSimpleDTO, Integer> service) {
        super(service);
    }


    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public PersonneSimpleDTO login(@RequestBody MdpDTO dto) throws Exception {
        return ((PersonneService)service).seloguer(dto);
    }

    @PostMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public boolean getEmail(@RequestBody MdpDTO dto)  {
        return ((PersonneService)service).selonEmail(dto);
    }

    @PostMapping("/activation_compte")
    @ResponseStatus(HttpStatus.OK)
    public boolean getActivated(@RequestBody String codeActivation) throws NoSuchAlgorithmException {
        return ((PersonneService)service).isActive(codeActivation);
    }

    @PostMapping("/creat")
    @ResponseStatus(HttpStatus.OK)
    public void creatPersonne(@RequestBody CreatPersonne dto) throws NoSuchAlgorithmException, InvalidKeySpecException, PersonneSimpleExisteExeption, MessagingException {
        ((PersonneService) service).creatPersonne(dto);
    }

    @PostMapping("/likes")
    @ResponseStatus(HttpStatus.OK)
    public void like(@RequestBody LikeBien like) throws Exception {
        ((PersonneService)service).likes(like);
    }

    @PostMapping("/favory")
    @ResponseStatus(HttpStatus.OK)
    public void favory(@RequestBody FavoryDTO favory) throws Exception {
        ((PersonneService)service).myFavory(favory);
    }
}
