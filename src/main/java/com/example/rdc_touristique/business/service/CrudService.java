package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.exeption.*;

import java.util.List;

public interface CrudService<DTO, ID>{

    //Creat
    void creat(DTO toDTO) throws ElementAlreadyExistsException;

    // read
    DTO readOne(ID id) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption;
    List<DTO> readAll();

    // update
    void update(DTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption;

    // delete
    void delete(ID toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption;
}
