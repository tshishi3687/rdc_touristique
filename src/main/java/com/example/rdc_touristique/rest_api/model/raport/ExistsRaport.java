package com.example.rdc_touristique.rest_api.model.raport;

import lombok.Getter;

@Getter
public class ExistsRaport extends ElementRapport{
    public ExistsRaport(Object id, String elementType) {
        super(id, elementType, "Un élément avec l'id mentionné existe déjà");
    }
}
