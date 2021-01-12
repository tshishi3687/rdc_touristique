package com.example.rdc_touristique.business.service;

import com.example.rdc_touristique.business.dto.ServiceDTO;
import com.example.rdc_touristique.business.mapper.Mapper;
import com.example.rdc_touristique.data_access.repository.ServiceRepository;
import com.example.rdc_touristique.exeption.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService implements CrudService<ServiceDTO, Integer> {

    @Autowired
    private Mapper<ServiceDTO, com.example.rdc_touristique.data_access.entity.Service> serviceMapper;
    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public void creat(ServiceDTO toCreat) throws ElementAlreadyExistsException {
        if (serviceRepository.existsById(toCreat.getId()))
            throw new ServiceExisteExeption(toCreat.getId());

        serviceRepository.save(serviceMapper.toEntity(toCreat));
    }

    @Override
    public ServiceDTO readOne(Integer integer) throws ServiceFoundExeption {
        com.example.rdc_touristique.data_access.entity.Service entity = serviceRepository.findById(integer)
                .orElseThrow(()-> new ServiceFoundExeption(integer));

        return serviceMapper.toDTO(entity);
    }

    @Override
    public List<ServiceDTO> readAll() {
        return serviceRepository.findAll().stream()
                .map(serviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(ServiceDTO toUpdate) throws ServiceFoundExeption {
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
