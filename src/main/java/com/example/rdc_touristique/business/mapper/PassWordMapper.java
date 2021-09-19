package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.PassWordDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.PassWord;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class PassWordMapper implements Mapper<PassWordDTO, PassWord>{

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> personneMapper;
    @Autowired
    private PersonneReposytory personneReposytory;

    @Override
    public PassWordDTO toDTO(PassWord passWord) {
        if (passWord == null)
            return null;
        return null;
    }

    @Override
    public PassWord toEntity(PassWordDTO passWordDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if (passWordDTO==null)
            return null;

        PassWord passWord = new PassWord();
        passWord.setId(passWordDTO.getId());
        passWord.setMdp(hasMdp(passWordDTO.getMdp()));
        passWord.setAppartienA(personneReposytory.getOne(passWordDTO.getAppartienA().getId()));
        return passWord;
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
