package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.CreatPersonneDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.RollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class PersonneCreaMapper implements Mapper<CreatPersonneDTO, Personne>{

    @Autowired
    private RollRepository rollRepository;

    @Override
    public CreatPersonneDTO toDTO(Personne personne) {
        return null;
    }

    @Override
    public Personne toEntity(CreatPersonneDTO creatPersonne) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(creatPersonne==null)
            return null;

        Personne personne = new Personne();
        personne.setId(0);
        personne.setNom(creatPersonne.getNom());
        personne.setPrenom(creatPersonne.getPrenom());
        personne.setDdn(creatPersonne.getDdn());
        personne.setRoleId(rollRepository.findByNomRole(creatPersonne.getRoll().getNomRoll()));
        personne.setActive(false);

        return personne;
    }


}
