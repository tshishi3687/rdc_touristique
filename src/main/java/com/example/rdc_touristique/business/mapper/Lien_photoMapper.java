package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.Lien_photoDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Lien_photo;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.ProvinceRepository;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import org.hibernate.engine.jdbc.BlobProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;

@Component
public class Lien_photoMapper implements Mapper<Lien_photoDTO, Lien_photo>{
    @Autowired
    private Mapper<ProvinceDTO, Province> provinceMapper;
    @Autowired
    private Mapper<VilleDTO, Ville> villeMapper;
    @Autowired
    private Mapper<BienDTO, Bien> bienleMapper;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private BienRepository bienRepository;

    @Override
    public Lien_photoDTO toDTO(Lien_photo lien_photo) {

        if(lien_photo==null)
            return null;


        return new Lien_photoDTO(
                lien_photo.getId(),
                lien_photo.getImage(),
                provinceMapper.toDTO(lien_photo.getProvince()),
                villeMapper.toDTO(lien_photo.getVille()),
                bienleMapper.toDTO(lien_photo.getBien())
        );
    }

    @Override
    public Lien_photo toEntity(Lien_photoDTO lien_photoDTO) {
        if(lien_photoDTO==null)
            return null;

        Lien_photo lien_photo = new Lien_photo();
        lien_photo.setId(lien_photoDTO.getId());
        lien_photo.setImage(lien_photoDTO.getImage());
        provinceRepository.getOne(lien_photoDTO.getProvince().getId());
        villeRepository.getOne(lien_photoDTO.getVille().getId());
        bienRepository.getOne(lien_photoDTO.getBien().getId());


        return lien_photo;
    }
}
