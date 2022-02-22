package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.DocOfficielDTO;
import com.example.rdc_touristique.data_access.entity.DocOfficielPersonne;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class DocOfficielMapper implements Mapper<DocOfficielDTO, DocOfficielPersonne>{


    @Override
    public DocOfficielDTO toDTO(DocOfficielPersonne docOfficiel) {
        if (docOfficiel==null)
            return null;

        return new DocOfficielDTO(
        );
    }

    @Override
    public DocOfficielPersonne toEntity(DocOfficielDTO docOfficielDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return null;
    }
}
