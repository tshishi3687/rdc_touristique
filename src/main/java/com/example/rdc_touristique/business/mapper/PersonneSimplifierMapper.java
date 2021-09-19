package com.example.rdc_touristique.business.mapper;


import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.stereotype.Component;

@Component
public class PersonneSimplifierMapper implements Mapper<PersonneSimplifierDTO, Personne> {
    @Override
    public PersonneSimplifierDTO toDTO(Personne personne) {
        if(personne==null)
            return null;
        return new PersonneSimplifierDTO(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom()
        );
    }

    @Override
    public Personne toEntity(PersonneSimplifierDTO personneSimplifierDTO) {
        if(personneSimplifierDTO==null)
            return null;


        Personne personne = new Personne();
        personne.setId(personneSimplifierDTO.getId());
        personne.setNom(personneSimplifierDTO.getNom());
        personne.setPrenom(personneSimplifierDTO.getPrenom());
        return personne;
    }
}
