package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.DocOfficielDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.business.service.DocOfficielService;
import com.example.rdc_touristique.data_access.entity.DocOfficielPersonne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

@RestController
@RequestMapping(path = "doc")
public class DocOfficielController extends AbstratCrudController<DocOfficielDTO, Integer> {
    public DocOfficielController(CrudService<DocOfficielDTO,Integer> service){super(service);}

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity.BodyBuilder uploadDoc(@RequestParam("imageFile") List<MultipartFile> file, @RequestParam("bien")int personneId) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        return ((DocOfficielService)service).uploadDoc(file, personneId);
    }

    @PostMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<DocOfficielPersonne> allImageByBienid(@RequestBody PersonneSimplifierDTO personneId){
        return ((DocOfficielService)service).getImage(personneId);
    }
}
