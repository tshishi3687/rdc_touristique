package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonneSimpleMaper implements Mapper<PersonneSimpleDTO, Personne>{

    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordorMapper;
    @Autowired
    private CoordonneeRepository coordorRepository;


    @Override
    public PersonneSimpleDTO toDTO(Personne personne) {
        if(personne==null)
            return null;

        return new PersonneSimpleDTO(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getDdn(),
                null,
                personne.getTelephone(),
                personne.getEmail(),
                personne.getStatus()
        );
    }

    @Override
    public Personne toEntity(PersonneSimpleDTO personneSimpleDTO) {
        if(personneSimpleDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneSimpleDTO.getId());
        personne.setNom(personneSimpleDTO.getNom());
        personne.setPrenom(personneSimpleDTO.getPrenom());
        personne.setDdn(personneSimpleDTO.getDdn());
        personne.setMdp(personneSimpleDTO.getMdp());
        personne.setTelephone(personneSimpleDTO.getTelephone());
        personne.setEmail(personneSimpleDTO.getEmail());
        return personne;
    }
}
