package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.data_access.entity.Personne;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;

@Component
public class PersonneSimpleMaper implements Mapper<PersonneSimpleDTO, Personne>{


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
                personne.getStatus(),
                personne.getDateCreation(),
                personne.getSuperid()
        );
    }

    @Override
    public Personne toEntity(PersonneSimpleDTO personneSimpleDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(personneSimpleDTO==null)
            return null;

        Personne personne = new Personne();
        personne.setId(personneSimpleDTO.getId());
        personne.setNom(personneSimpleDTO.getNom());
        personne.setPrenom(personneSimpleDTO.getPrenom());
        personne.setDdn(personneSimpleDTO.getDdn());
        personne.setMdp(hasMdp(personneSimpleDTO.getMdp()));
        personne.setTelephone(personneSimpleDTO.getTelephone());
        personne.setEmail(personneSimpleDTO.getEmail());
        personne.setStatus("client");
        personne.setDateCreation(LocalDateTime.now());
        personne.setSuperid(0);
        return personne;
    }

    private String hasMdp(String mdp) throws NoSuchAlgorithmException, InvalidKeySpecException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(mdp.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
        for (int i=0;i<byteData.length;i++) {
            String hex=Integer.toHexString(0xff & byteData[i]);
            if(hex.length()==1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
