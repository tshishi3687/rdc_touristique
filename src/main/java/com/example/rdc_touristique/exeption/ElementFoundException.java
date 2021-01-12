package com.example.rdc_touristique.exeption;

import lombok.Getter;

@Getter
public abstract class ElementFoundException extends Exception{
    private final Object id;
    private final Class<?> clazz;

    public ElementFoundException(Class<?> ofClass, Object id) {
        super("L'element de classe " + ofClass.getSimpleName() + " d'id(" + id + ") n'a pas été trouvé");
        this.id = id;
        this.clazz = ofClass;
    }
}
