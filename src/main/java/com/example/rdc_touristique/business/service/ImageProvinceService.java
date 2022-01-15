package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ImageProvinceDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ImageProvince;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.repository.ImageProvinceRepository;
import com.example.rdc_touristique.data_access.repository.ProvinceRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ImageProvinceService implements CrudService<ImageProvinceDTO,Integer> {

    @Autowired
    private ImageProvinceRepository imageProvinceRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private Mapper<ProvinceDTO, Province> provinceMapper;


    @Override
    public void creat(ImageProvinceDTO toDTO) throws ElementAlreadyExistsException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption {

    }

    @Override
    public ImageProvinceDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {
        return null;
    }

    @Override
    public List<ImageProvinceDTO> readAll() {
        return null;
    }

    @Transactional
    public List<ImageProvince> getAll(){

        List<ImageProvince> listProvince = imageProvinceRepository.findAll();
        List<ImageProvince> newListImageProvinve = new ArrayList<>();

        for (ImageProvince imgPro : listProvince) {
            ImageProvince imgg = new ImageProvince();
            imgg.setName(imgPro.getName());
            imgg.setType(imgPro.getType());
            imgg.setPicByte(decompressBytes(imgPro.getPicByte()));
            imgg.setProvinceID(imgg.getProvinceID());
            newListImageProvinve.add(imgg);
        }
        return newListImageProvinve;
    }

    @Override
    public void update(ImageProvinceDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, ActionFoundExeption, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption, ActionFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException, DureeLocationFoundExeption, RollFoundExeption, AdressUserFoundExeption, ContactUserFoundExeption, InfoBancaireFoundExeption {

    }

    @Transactional
    public void uploadImage(List<MultipartFile> file, int id) throws IOException{

        for (MultipartFile multipartFile : file) {
            ImageProvince img = new ImageProvince();
            img.setName(multipartFile.getOriginalFilename());
            img.setType(multipartFile.getContentType());
            img.setPicByte(compressBytes(multipartFile.getBytes()));
            img.setProvinceID(provinceRepository.getOne(id));
            imageProvinceRepository.save(img);
        }
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
