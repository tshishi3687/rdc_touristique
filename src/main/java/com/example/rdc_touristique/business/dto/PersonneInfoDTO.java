package com.example.rdc_touristique.business.dto;

import com.example.rdc_touristique.data_access.entity.AdressUser;
import com.example.rdc_touristique.data_access.entity.DocOfficiel;
import com.example.rdc_touristique.data_access.entity.InfoBancaire;
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
    private List<ReservationDTO> reservation;
    private List<InfoBancaire> infoBancaires;
    private List<DocOfficiel> docOfficiels;
    private List<AdressUser> adressUser;
}
