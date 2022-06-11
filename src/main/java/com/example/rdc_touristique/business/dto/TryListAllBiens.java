package com.example.rdc_touristique.business.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TryListAllBiens {
    private int typeId;
    private int provinceId;
    private int villeId;
    private int Page;
}
