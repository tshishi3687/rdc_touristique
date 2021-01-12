package com.example.rdc_touristique.rest_api.model.container;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ElementsContainer<DTO> {

    private final int count;
    private final List<DTO> list;

    public ElementsContainer(List<DTO> list) {
        this.list = list;
        this.count = list.size();
    }
}
