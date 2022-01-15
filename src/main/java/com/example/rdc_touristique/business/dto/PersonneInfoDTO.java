package com.example.rdc_touristique.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonneInfoDTO implements IdentifiedDTO<Integer>{

    private Integer id;
    private List<BienDTO> bien;
    private InfoBancaireDTO infoBancaires;
    private DocOfficielDTO docOfficiels;
    private AdressUserDTO adressUser;
}
