package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienMisEnLigneDTO;
import com.example.rdc_touristique.business.dto.BienVuDTO;
import com.example.rdc_touristique.business.dto.ContratDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.BienMisEnLigne;
import com.example.rdc_touristique.data_access.entity.Contrat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class BienMisEnLigneMapper implements Mapper<BienMisEnLigneDTO, BienMisEnLigne>{

    @Autowired
    private Mapper<BienVuDTO, Bien> bienMapper;
    @Autowired
    private Mapper<ContratDTO, Contrat> contratMapper;

    @Override
    public BienMisEnLigneDTO toDTO(BienMisEnLigne bienMisEnLigne) {
        if (bienMisEnLigne==null)
            return null;
        return new BienMisEnLigneDTO(
                bienMisEnLigne.getId(),
                bienMapper.toDTO(bienMisEnLigne.getBienLie()),
                contratMapper.toDTO(bienMisEnLigne.getContratBienMisEnLigne()),
                null
        );
    }

    @Override
    public BienMisEnLigne toEntity(BienMisEnLigneDTO bienMisEnLigneDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Créé manuellement dans le BienService
        return null;
    }
}
