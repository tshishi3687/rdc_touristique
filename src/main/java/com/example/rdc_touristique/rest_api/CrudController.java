package com.example.rdc_touristique.rest_api;

import com.example.rdc_touristique.business.dto.IdentifiedDTO;
import com.example.rdc_touristique.exeption.ActionFoundExeption;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import com.example.rdc_touristique.exeption.ElementFoundException;
import com.example.rdc_touristique.exeption.FoundExeption;
import com.example.rdc_touristique.rest_api.model.container.ElementsContainer;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface CrudController<DTO extends IdentifiedDTO<ID>, ID> {

    // Create
    void create(@RequestBody DTO dto) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException;

    // Read
    ResponseEntity<DTO> getOne(@PathVariable ID id) throws ElementFoundException, FoundExeption;
    ResponseEntity<ElementsContainer<DTO>> getAll();

    // Update
    ResponseEntity<String> update(@RequestBody DTO dto) throws ElementFoundException, FoundExeption, NoSuchAlgorithmException, InvalidKeySpecException;

    // Delete
    ResponseEntity<DTO> delete(@PathVariable ID id) throws ElementFoundException, FoundExeption, NoSuchAlgorithmException, InvalidKeySpecException;
}
