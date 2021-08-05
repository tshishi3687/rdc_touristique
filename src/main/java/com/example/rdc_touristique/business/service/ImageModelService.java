package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageModelService implements CrudService<ImageModelDTO, Integer>{


    @Autowired
    ImageRepository imageRepository;
    private ResponseEntity.BodyBuilder rest[];

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
    public ResponseEntity.BodyBuilder uploadImage(List<MultipartFile> file, int superid) throws IOException {
        System.out.println(superid);
        for (MultipartFile multipartFile : file) {
            ImageModel img = new ImageModel(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
                    compressBytes(multipartFile.getBytes()), superid);
            imageRepository.save(img);
        }
        return ResponseEntity.status(HttpStatus.OK);
    }

    @Transactional
    public ImageModel getImage(String imageName) throws IOException{
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()), imageRepository.findByName(imageName).get().getSuperid());
        return img;
    }

    @Transactional
    public List<ImageModel> getAllBySuperid(int id) throws IOException{
        return new ArrayList<>(imageRepository.findBySuperid(id));
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
        } catch (IOException e) {
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
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
