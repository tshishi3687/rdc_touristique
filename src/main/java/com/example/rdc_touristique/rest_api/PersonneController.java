package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.PersonneService;
import com.example.rdc_touristique.exeption.ContratMisEnLigneExisteExeption;
import com.example.rdc_touristique.exeption.PersonneSimpleExisteExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@RestController
@RequestMapping("personne")
public class PersonneController extends AbstratCrudController<PersonneSimpleDTO, Integer>{

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    BCryptPasswordEncoder encoder;

    public PersonneController(CrudService<PersonneSimpleDTO, Integer> service) {
        super(service);
    }


    @RequestMapping({ "/hello" })
    public String firstPage() {
        return "Hello World";
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> login(@RequestBody MdpDTO dto) {
        return ((PersonneService)service).seloguer(dto);
    }

    @PostMapping("/ibau")
    @ResponseStatus(HttpStatus.OK)
    public boolean verifIBAU() {
        return ((PersonneService)service).infoBanAdreUser();
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
    public void creatPersonne(@RequestBody CreatPersonneDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException, PersonneSimpleExisteExeption, MessagingException {
        ((PersonneService) service).creatPersonne(dto);
    }

    @PostMapping("/change_passe")
    @ResponseStatus(HttpStatus.OK)
    public boolean changePasse(@RequestBody CreatPersonneDTO dto) throws NoSuchAlgorithmException, MessagingException {
        return ((PersonneService) service).modifMDP(dto);
    }

    @PostMapping("/likes")
    @ResponseStatus(HttpStatus.OK)
    public void like(@RequestBody LikeBienDTO like) throws Exception {
        ((PersonneService)service).likes(like);
    }

    @GetMapping("/info_personne")
    public PersonneVuDTO infoPersonne(){
        return ((PersonneService)service).infoPersonne();
    }

    @GetMapping("/nbcompte")
    public long nbCompte(){
        return ((PersonneService)service).nbCompte();
    }

    @PostMapping("/favory")
    @ResponseStatus(HttpStatus.OK)
    public void favory(@RequestBody FavoryDTO favory) throws Exception {
        ((PersonneService)service).myFavory(favory);
    }

    @PostMapping("/mdp_modif")
    @ResponseStatus(HttpStatus.OK)
    public boolean modifMDP(@RequestBody ModifPassDTO modifMDPDTO) throws Exception {
        return ((PersonneService)service).modifMDP(modifMDPDTO);
    }

    @PostMapping("/alercontratM")
    @ResponseStatus(HttpStatus.OK)
    public void alertContrat(@RequestBody int dtoId) throws MessagingException, ContratMisEnLigneExisteExeption, NoSuchAlgorithmException {
        ((PersonneService)service).alertStopContrat(dtoId);
    }
}
