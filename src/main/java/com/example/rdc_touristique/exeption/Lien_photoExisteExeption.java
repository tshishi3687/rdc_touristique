package com.example.rdc_touristique.exeption;

public class Lien_photoExisteExeption extends ElementAlreadyExistsException{
    public Lien_photoExisteExeption(Object id, Class<?> clazz) {
        super(id, clazz);
    }
}
