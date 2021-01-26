package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.dto.ServiceDTO;
import com.example.rdc_touristique.business.dto.TypeDTO;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.data_access.entity.Service;
import com.example.rdc_touristique.data_access.entity.Type;
import com.example.rdc_touristique.data_access.repository.CoordonneeRepository;
import com.example.rdc_touristique.data_access.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper implements Mapper<ServiceDTO, Service>{
    @Autowired
    private Mapper<CoordonneeDTO, Coordonnee> coordonneeMapper;
    @Autowired
    private Mapper<TypeDTO, Type> typeMapper;
    @Autowired
    private CoordonneeRepository coordorRepository;
    @Autowired
    private TypeRepository typeRepository;

    @Override
    public ServiceDTO toDTO(Service service) {
        if(service == null)
            return null;
        return new ServiceDTO(
                service.getId(),
                service.getNom(),
                typeMapper.toDTO(service.getType()),
                coordonneeMapper.toDTO(service.getCoordonnee())
        );
    }

    @Override
    public Service toEntity(ServiceDTO serviceDTO) {
        if(serviceDTO==null)
            return null;

        Service service = new Service();
        service.setId(serviceDTO.getId());
        service.setNom(serviceDTO.getNom());
        service.setType(typeRepository.getOne(serviceDTO.getType().getId()));
        service.setCoordonnee(coordonneeMapper.toEntity(serviceDTO.getCoordonnee()));

        return service;
    }
}
