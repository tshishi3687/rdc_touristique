package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.exeption.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public interface CrudService<DTO, ID>{

    //Creat
    void creat(DTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException;

    // read
    DTO readOne(ID id) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, PaysFoundExeption, ContratMisEnLigneFoundExeption;
    List<DTO> readAll();

    // update
    void update(DTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, ContratMisEnLigneExisteExeption, MessagingException;

    // delete
    void delete(ID toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption;
}
