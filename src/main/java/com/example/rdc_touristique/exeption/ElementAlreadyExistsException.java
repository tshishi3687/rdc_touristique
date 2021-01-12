package com.example.rdc_touristique.exeption;

import lombok.Getter;

@Getter
public abstract class ElementAlreadyExistsException extends Exception{
    private final Object id;
    private final Class<?> clazz;

    public ElementAlreadyExistsException( Object id, Class<?> clazz) {
        super("L'element que vous voulez créer existe deja " +
                "\n\t-classe: " + clazz.getSimpleName() + " ;" +
                "\n\t-id : " + id + " ;");
        this.id = id;
        this.clazz = clazz;
    }
}
