package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TryListAllBiens {
    private String typeId;
    private String provinceId;
    private String villeId;
    private int Page;
    private int nbPage;
    private List<BienVuSimplifierDTO> list;
}
