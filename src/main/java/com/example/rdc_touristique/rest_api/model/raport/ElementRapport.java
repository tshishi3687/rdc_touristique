package com.example.rdc_touristique.rest_api.model.raport;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ElementRapport {

    private final Object id;
    private final String elementType;
    private final String message;
}
