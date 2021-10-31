package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.ServiceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.business.mapper.ServiceMapper;
import com.example.rdc_touristique.business.mapper.VilleMapper;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.Ville;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.data_access.repository.ServiceRepository;
import com.example.rdc_touristique.data_access.repository.VilleRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService implements CrudService<ServiceDTO, Integer> {

    @Autowired
    private Mapper<ServiceDTO, com.example.rdc_touristique.data_access.entity.Service> serviceMapper;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private Mapper<VilleDTO, Ville> villeMapper;
    @Autowired
    private VilleRepository villeRepository;


    @Transactional
    public List<ServiceDTO> selonLaVille(VilleDTO ville) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return serviceRepository.findAllByCoordonnee_Ville(villeRepository.getOne(ville.getId())).stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void creat(ServiceDTO toCreat) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (serviceRepository.existsById(toCreat.getId()))
            throw new ServiceExisteExeption(toCreat.getId());
        com.example.rdc_touristique.data_access.entity.Service entity = serviceMapper.toEntity(toCreat);
        coordorRepository.save(entity.getCoordonnee());
        serviceRepository.save(entity);
    }

    @Override
    public ServiceDTO readOne(Integer integer) throws ServiceFoundExeption {
        com.example.rdc_touristique.data_access.entity.Service entity = serviceRepository.findById(integer)
                .orElseThrow(()-> new ServiceFoundExeption(integer));

        return serviceMapper.toDTO(entity);
    }

    @Override
    public List<ServiceDTO> readAll() {
        return serviceRepository.findByOrderByIdDesc().stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ServiceDTO toUpdate) throws ServiceFoundExeption, NoSuchAlgorithmException, InvalidKeySpecException {
        if( !serviceRepository.existsById( toUpdate.getId() ))
            throw new ServiceFoundExeption(toUpdate.getId());

        serviceRepository.save( serviceMapper.toEntity(toUpdate) );
    }

    @Override
    public void delete(Integer toDelete) throws ServiceFoundExeption {
        if( !serviceRepository.existsById(toDelete))
            throw new ServiceFoundExeption(toDelete);

        serviceRepository.deleteById(toDelete);
    }
}
