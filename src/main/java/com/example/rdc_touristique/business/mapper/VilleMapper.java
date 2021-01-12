package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VilleMapper implements Mapper<VilleDTO, Ville>{
    @Autowired
    private Mapper<ProvinceDTO, Province> provinceMapper;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public VilleDTO toDTO(Ville ville) {
        if (ville==null)
            return null;

        return new VilleDTO(
                ville.getId(),
                ville.getNom_ville(),
                ville.getNhabitant(),
                provinceMapper.toDTO(ville.getProvince()),
                ville.getDesciption()

        );
    }

    @Override
    public Ville toEntity(VilleDTO villeDTO) {
        if(villeDTO==null)
            return null;

        Ville ville = new Ville();
        ville.setId(villeDTO.getId());
        ville.setNom_ville(villeDTO.getNom_ville());
        ville.setNhabitant(villeDTO.getNhabitant());
        ville.setProvince(provinceRepository.getOne(villeDTO.getProvince().getId()));
        ville.setDesciption(villeDTO.getDescription());

        return ville;
    }
}
