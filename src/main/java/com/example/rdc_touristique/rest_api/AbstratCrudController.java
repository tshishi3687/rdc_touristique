package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.IdentifiedDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public abstract class AbstratCrudController<DTO extends IdentifiedDTO<ID>,ID> implements CrudController<DTO, ID> {

    protected final CrudService<DTO, ID> service;

    public AbstratCrudController(CrudService<DTO, ID> service){this.service = service;}

    // CREATE - POST > http://localhost:8081/?
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Valid DTO dto) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException {
        service.creat(dto);

    }

    // READ_ONE - GET > http://localhost:8081/?/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DTO> getOne(@PathVariable ID id) throws Exception {
        return ResponseEntity.ok( service.readOne(id) );
    }



    // READ_ALL - GET > http://localhost:8081/?
    @GetMapping
    public ResponseEntity<List<DTO>> getAll() throws Exception {
        List<DTO> list = service.readAll();
        return ResponseEntity.ok( list );
    }

    // UPDATE - PUT > http://localhost:8081/?
    @PutMapping
    public ResponseEntity<String> update(@RequestBody DTO dto) throws ElementFoundException, FoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, MessagingException, ContratMisEnLigneExisteExeption {
        service.update(dto);
        return ResponseEntity.ok("Un élément a été modifié" );
    }

    // DELETE - DELETE > http://localhost:8081/?/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> delete(@PathVariable ID id) throws Exception {
        DTO dto = service.readOne(id);
        service.delete(id);
        return ResponseEntity.ok(dto);
    }
}
