package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ImageModelDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.entity.ImageModel;
import com.example.rdc_touristique.data_access.entity.Province;
import com.example.rdc_touristique.data_access.repository.ImageRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageModelService implements CrudService<ImageModelDTO, Integer>{

    @Autowired
    private Mapper<ImageModelDTO, ImageModel> imageModelDTOMapper;
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void creat(ImageModelDTO toDTO) throws ElementAlreadyExistsException {
        if (imageRepository.existsById(toDTO.getId()))
            throw new ProvinceExisteExeption(toDTO.getId());

        System.out.println(toDTO);
        imageRepository.save(imageModelDTOMapper.toEntity(toDTO));
    }
    @Transactional
    public void save(ImageModelDTO toDTO) throws ElementAlreadyExistsException {
        if (imageRepository.existsById(toDTO.getId()))
            throw new ProvinceExisteExeption(toDTO.getId());

        System.out.println(toDTO);
        imageRepository.save(imageModelDTOMapper.toEntity(toDTO));
    }

    @Override
    public ImageModelDTO readOne(Integer integer) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {
        ImageModel entity = imageRepository.findById(integer)
                .orElseThrow(()-> new ProvinceFoundExeption(integer));

        return imageModelDTOMapper.toDTO(entity);
    }

    @Override
    public List<ImageModelDTO> readAll() {
        return imageRepository.findAll().stream()
                .map(imageModelDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ImageModelDTO toUpdate) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {
        if( !imageRepository.existsById( toUpdate.getId() ))
            throw new ProvinceFoundExeption(toUpdate.getId());

        imageRepository.save( imageModelDTOMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws FoundExeption, BienFoundExeption, CoordonneeFoundExeption, Lien_photoFoundExeption, PersonneSimpleFoundExeption, ProvinceFoundExeption, ReservationFoundExeption, ServiceFoundExeption, Type_bienFoundExeption, TypeFoundExeption, VilleFoundExeption {
        if( !imageRepository.existsById(toDelete))
            throw new ProvinceFoundExeption(toDelete);

        imageRepository.deleteById(toDelete);
    }
}
