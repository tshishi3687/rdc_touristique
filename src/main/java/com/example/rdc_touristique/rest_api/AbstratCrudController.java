package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.IdentifiedDTO;
import com.example.rdc_touristique.business.service.CrudService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import com.example.rdc_touristique.exeption.ElementFoundException;
import com.example.rdc_touristique.exeption.FoundExeption;
import com.example.rdc_touristique.rest_api.model.container.ElementsContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public abstract class AbstratCrudController<DTO extends IdentifiedDTO<ID>,ID> implements CrudController<DTO, ID> {

    protected final CrudService<DTO, ID> service;

    public AbstratCrudController(CrudService<DTO, ID> service){this.service = service;}

    // CREATE - POST > http://localhost:8081/?
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody @Valid DTO dto) throws ElementAlreadyExistsException {
        service.creat(dto);

    }

    // READ_ONE - GET > http://localhost:8081/?/{id}
    @GetMapping("/{id}")
    public ResponseEntity<DTO> getOne(@PathVariable ID id) throws ElementFoundException, FoundExeption {
        return ResponseEntity.ok( service.readOne(id) );
    }



    // READ_ALL - GET > http://localhost:8081/?
    @GetMapping
    public ResponseEntity<ElementsContainer<DTO>> getAll(){
        List<DTO> list = service.readAll();
        return ResponseEntity.ok( new ElementsContainer<>( list ) );
    }

    // UPDATE - PUT > http://localhost:8081/?
    @PutMapping
    public ResponseEntity<String> update(@RequestBody DTO dto) throws ElementFoundException, FoundExeption {
        service.update(dto);
        return ResponseEntity.ok("Un élément a été modifié" );
    }

    // DELETE - DELETE > http://localhost:8081/?/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<DTO> delete(@PathVariable ID id) throws ElementFoundException, FoundExeption {
        DTO dto = service.readOne(id);
        service.delete(id);
        return ResponseEntity.ok(dto);
    }
}
