package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.data_access.entity.*;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
import com.example.rdc_touristique.data_access.repository.ProvinceRepository;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.zip.Deflater;

@Component
public class Lien_photoMapper implements Mapper<Lien_photoDTO, Lien_photo>{
    @Autowired
    private Mapper<ProvinceDTO, Province> provinceMapper;
    @Autowired
    private Mapper<VilleDTO, Ville> villeMapper;
    @Autowired
    private Mapper<BienDTO, Bien> bienleMapper;

    @Autowired
    private Mapper<ImageModelDTO, ImageModel> imageModelMapper;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private BienRepository bienRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public Lien_photoDTO toDTO(Lien_photo lien_photo) {

        if(lien_photo==null)
            return null;
        System.out.println(lien_photo);

        return new Lien_photoDTO(
                lien_photo.getId(),
                imageModelMapper.toDTO(lien_photo.getImage()),
                provinceMapper.toDTO(lien_photo.getProvince()),
                villeMapper.toDTO(lien_photo.getVille()),
                bienleMapper.toDTO(lien_photo.getBien())
        );
    }

    @Override
    public Lien_photo toEntity(Lien_photoDTO lien_photoDTO) {
        if(lien_photoDTO==null)
            return null;

        System.out.println(lien_photoDTO);

        Lien_photo lien_photo = new Lien_photo();
        lien_photo.setId(lien_photoDTO.getId());
        lien_photo.setImage(imageRepository.getOne(lien_photo.getImage().getId()));
        lien_photo.setProvince(provinceRepository.getOne(lien_photoDTO.getProvince().getId()));
        lien_photo.setVille(villeRepository.getOne(lien_photoDTO.getVille().getId()));
        lien_photo.setBien(bienRepository.getOne(lien_photoDTO.getBien().getId()));

        return lien_photo;
    }
}
