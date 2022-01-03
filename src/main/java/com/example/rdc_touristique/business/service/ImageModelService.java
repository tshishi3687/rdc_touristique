package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.ImageBien;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
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
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Component
public class ImageModelService implements CrudService<ImageModelDTO, Integer>{


    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private CoordonneeRepository coordorRepository;

    @Override
    public void creat(ImageModelDTO toDTO) throws ElementAlreadyExistsException {

    }

    @Override
    public ImageModelDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {
        return null;
    }

    @Override
    public List<ImageModelDTO> readAll() {
        return null;
    }

    @Override
    public void update(ImageModelDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {

    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {

    }

    @Transactional
    public void uploadImage(List<MultipartFile> file, int id) throws IOException{

        for (MultipartFile multipartFile : file) {
            ImageBien img = new ImageBien();
            img.setName(multipartFile.getOriginalFilename());
            img.setType(multipartFile.getContentType());
            img.setPicByte(compressBytes(multipartFile.getBytes()));
            img.setBienid(bienRepository.getOne(id));
            imageRepository.save(img);
        }
    }


    @Transactional
    public List<ImageBien> getImage(int idBien) {
        if(idBien <= 0)
            return null;

        List<ImageBien> listBien = imageRepository.findAllByBienid(bienRepository.getOne(idBien));
        List<ImageBien> newListImageBien = new ArrayList<>();

        for (ImageBien imageBien : listBien) {
            ImageBien imgg = new ImageBien();
            imgg.setName(imageBien.getName());
            imgg.setType(imageBien.getType());
            imgg.setPicByte(decompressBytes(imageBien.getPicByte()));
            newListImageBien.add(imgg);
        }
        return newListImageBien;
    }

    @Transactional
    public void deleteImageBien(BienDTO bienDTO) throws NoSuchAlgorithmException, InvalidKeySpecException, BienExisteExeption {
        if(!bienRepository.existsById(bienDTO.getId()))
            throw new BienExisteExeption(bienDTO.getId());
        imageRepository.deleteAllByBienid(bienMapper.toEntity(bienDTO));
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
