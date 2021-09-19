package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.DocOfficielDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.DocOfficiel;
import com.example.rdc_touristique.data_access.repository.DocofficielRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Component
public class DocOfficielService implements CrudService<DocOfficielDTO, Integer>{

    @Autowired
    private DocofficielRepository docofficielRepository;
    @Autowired
    private PersonneReposytory personneReposytory;


    @Override
    public void creat(DocOfficielDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public DocOfficielDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {
        return null;
    }

    @Override
    public List<DocOfficielDTO> readAll() {
        return null;
    }

    @Override
    public void update(DocOfficielDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Transactional
    public ResponseEntity.BodyBuilder uploadDoc(List<MultipartFile> file, int id) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException  {

        for (MultipartFile multipartFile : file) {
            DocOfficiel doc = new DocOfficiel();
            doc.setName(multipartFile.getOriginalFilename());
            doc.setType(multipartFile.getContentType());
            doc.setPicByte(compressBytes(multipartFile.getBytes()));
            doc.setPersonneId(personneReposytory.getOne(id));

            docofficielRepository.save(doc);
        }

        return ResponseEntity.status(HttpStatus.OK);
    }

    @Transactional
    public List<DocOfficiel> getImage(PersonneSimplifierDTO person) {
        if(person == null)
            return null;

        List<DocOfficiel> listDoc = docofficielRepository.findAllByPersonneId(personneReposytory.getOne(person.getId()));
        List<DocOfficiel> newListDoc = new ArrayList<>();

        for (DocOfficiel docOfficiel : listDoc) {
            DocOfficiel imgg = new DocOfficiel();
            imgg.setName(docOfficiel.getName());
            imgg.setType(docOfficiel.getType());
            imgg.setPicByte(decompressBytes(docOfficiel.getPicByte()));
            newListDoc.add(imgg);
        }
        return newListDoc;
    }

    // compress the image bytes before storing it in the database
    private static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException ignored) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }
    // uncompress the image bytes before returning it to the angular application
    private static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ignored) {
        }
        return outputStream.toByteArray();
    }
}
