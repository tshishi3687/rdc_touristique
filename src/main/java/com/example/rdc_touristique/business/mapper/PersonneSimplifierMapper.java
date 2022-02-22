package com.example.rdc_touristique.business.mapper;


import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.security.config.JwtRequestFilter;
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

        return JwtRequestFilter.maPersonne();
    }
}
