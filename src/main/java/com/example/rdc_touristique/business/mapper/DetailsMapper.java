package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.DetailsDTO;
import com.example.rdc_touristique.data_access.entity.Details;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class DetailsMapper implements Mapper<DetailsDTO, Details>{
    @Override
    public DetailsDTO toDTO(Details details) {
        if(details==null)
            return null;
        return new DetailsDTO(
                details.getId(),
                details.getPayerId(),
                details.getCreateTime(),
                details.getUpdateTime(),
                details.getIntent(),
                details.getStatus(),
                details.getNom(),
                details.getPrenom(),
                details.getEmail(),
                details.getAdressLine1(),
                details.getAdminArea1(),
                details.getAdminArea2(),
                details.getCountryCode(),
                details.getPostalcode()
        );
    }

    @Override
    public Details toEntity(DetailsDTO detailsDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(detailsDTO==null)
            return null;

        Details details = new Details();
        details.setId(detailsDTO.getId());
        details.setPayerId(detailsDTO.getPayerId());
        details.setCreateTime(detailsDTO.getCreateTime());
        details.setUpdateTime(detailsDTO.getUpdateTime());
        details.setIntent(detailsDTO.getIntent());
        details.setStatus(detailsDTO.getStatus());
        details.setNom(detailsDTO.getNom());
        details.setPrenom(detailsDTO.getPrenom());
        details.setEmail(detailsDTO.getEmail());
        details.setAdressLine1(detailsDTO.getAdressLine1());
        details.setAdminArea1(detailsDTO.getAdminArea1());
        details.setAdminArea2(detailsDTO.getAdminArea2());
        details.setCountryCode(detailsDTO.getCountryCode());
        details.setPostalcode(detailsDTO.getPostalcode());
        return details;
    }
}
