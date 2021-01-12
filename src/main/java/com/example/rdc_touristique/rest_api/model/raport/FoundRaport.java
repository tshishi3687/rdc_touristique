package com.example.rdc_touristique.rest_api.model.raport;

public class FoundRaport extends ElementRapport{
    public FoundRaport(Object id, String elementType) {
        super(id, elementType, "Il n'existe pas l'élément ayant cet id");
    }
}
