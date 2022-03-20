package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.data_access.entity.ImageProvince;
import com.example.rdc_touristique.data_access.entity.ImageVille;
import com.example.rdc_touristique.data_access.repository.ImageVilleRepository;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
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
public class ImageVilleService implements CrudService {

    @Autowired
    private ImageVilleRepository imageVilleRepository;
    @Autowired
    private VilleRepository villeRepository;

    @Transactional
    public List<ImageVille> getImage(int villeId){
        List<ImageVille> listVille = imageVilleRepository.findAllByVille(villeRepository.getOne(villeId));
        List<ImageVille> newListVille = new ArrayList<>();
        for (ImageVille imageVille: listVille){
            ImageVille img = new ImageVille();
            img.setName(imageVille.getName());
            img.setType(imageVille.getType());
            img.setPicByte(decompressBytes(imageVille.getPicByte()));
            newListVille.add(img);
        }
        return newListVille;
    }

    @Transactional
    public void uploadImage(List<MultipartFile> file, int id) throws IOException {

        for (MultipartFile multipartFile : file) {
            ImageVille img = new ImageVille();
            img.setName(multipartFile.getOriginalFilename());
            img.setType(multipartFile.getContentType());
            img.setPicByte(compressBytes(multipartFile.getBytes()));
            img.setVille(villeRepository.getOne(id));
            imageVilleRepository.save(img);
        }
    }

    @Override
    public void creat(Object toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, MessagingException {

    }

    @Override
    public Object readOne(Object o) throws Exception {
        return null;
    }

    @Override
    public List readAll() throws Exception {
        return null;
    }

    @Override
    public void update(Object toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption, ContratMisEnLigneExisteExeption, MessagingException {

    }

    @Override
    public void delete(Object toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

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
