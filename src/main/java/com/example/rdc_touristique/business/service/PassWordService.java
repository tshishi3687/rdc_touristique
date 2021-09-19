package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.MdpDTO;
import com.example.rdc_touristique.business.dto.PassWordDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ContactUser;
import com.example.rdc_touristique.data_access.entity.PassWord;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.ContactUserRepository;
import com.example.rdc_touristique.data_access.repository.PassWordRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;

@Service
public class PassWordService implements CrudService<PassWordDTO, Integer> {

    @Autowired
    private Mapper<PersonneSimplifierDTO, Personne> mapper;
    @Autowired
    private Mapper<PassWordDTO, PassWord> passWordMapper;
    @Autowired
    private PassWordRepository passWordRepository;
    @Autowired
    private ContactUserRepository contactUserRepository;


    @Override
    public void creat(PassWordDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {
        if (passWordRepository.existsById(toDTO.getId()))
            throw new PassWordExisteExeption(toDTO.getId());

        List<PassWord> passWordList = passWordRepository.findAllByAppartienA(mapper.toEntity(toDTO.getAppartienA()));

        for (PassWord passWord : passWordList) {
            if (passWord.isMode()){
                passWord.setMode(false);
                passWordRepository.save(passWord);
            }
        }

        PassWord entity = passWordMapper.toEntity(toDTO);
        entity.setMode(true);
        passWordRepository.save(entity);
    }

    @Override
    public PassWordDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {
        return null;
    }

    @Override
    public List<PassWordDTO> readAll() {
        return null;
    }

    @Override
    public void update(PassWordDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }


    private String hasMdp(String mdp) throws NoSuchAlgorithmException {
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
        for (byte byteDatum : byteData) {
            String hex = Integer.toHexString(0xff & byteDatum);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
