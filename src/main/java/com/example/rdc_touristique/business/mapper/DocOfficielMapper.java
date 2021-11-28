package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.DocOfficielDTO;
import com.example.rdc_touristique.data_access.entity.DocOfficiel;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DocOfficielMapper implements Mapper<DocOfficielDTO, DocOfficiel>{


    @Override
    public DocOfficielDTO toDTO(DocOfficiel docOfficiel) {
        if (docOfficiel==null)
            return null;

        return new DocOfficielDTO(
        );
    }

    @Override
    public DocOfficiel toEntity(DocOfficielDTO docOfficielDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
