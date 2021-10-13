package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.BienDTO;
import com.example.rdc_touristique.business.dto.EtatDemandeDTO;
import com.example.rdc_touristique.business.dto.PersonneSimpleDTO;
import com.example.rdc_touristique.business.dto.DemandeDTO;
import com.example.rdc_touristique.data_access.entity.Bien;
import com.example.rdc_touristique.data_access.entity.EtatDemande;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.entity.Demande;
import com.example.rdc_touristique.data_access.repository.BienRepository;
import com.example.rdc_touristique.data_access.repository.EtatDEmandeRepository;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DemandeMapper implements Mapper<DemandeDTO, Demande>{
    @Autowired
    private Mapper<BienDTO, Bien> bienMapper;
    @Autowired
    private Mapper<PersonneSimpleDTO, Personne> personneMapper;
    @Autowired
    private BienRepository bienRepository;
    @Autowired
    private PersonneReposytory personneReposytory;
    @Autowired
    private EtatDEmandeRepository etatDEmandeRepository;
    @Autowired
    private Mapper<EtatDemandeDTO, EtatDemande> etatDemandeMapper;

    @Override
    public DemandeDTO toDTO(Demande demande) {
        if(demande ==null)
            return null;

        return new DemandeDTO(
                demande.getId(),
                demande.getDdj(),
                demande.getDda(),
                demande.getDdd(),
                demande.getNpersonne(),
                bienMapper.toDTO(demande.getBienDemandee()),
                personneMapper.toDTO(demande.getFaitPar()),
                etatDemandeMapper.toDTO(demande.getEtatDemande())
        );
    }

    @Override
    public Demande toEntity(DemandeDTO demandeDTO) {
        if(demandeDTO==null)
            return null;

        Demande demande = new Demande();
        demande.setId(demandeDTO.getId());
        demande.setDdj(LocalDateTime.now());
        demande.setDda(demandeDTO.getDda());
        demande.setDdd(demandeDTO.getDdd());
        demande.setNpersonne(demandeDTO.getNpersonne());
        demande.setBienDemandee(bienRepository.getOne(demandeDTO.getBienDemandee().getId()));
        demande.setFaitPar(personneReposytory.getOne(demandeDTO.getFaitPar().getId()));
        demande.setEtatDemande(etatDEmandeRepository.getOne(demandeDTO.getEtat().getId()));
        return demande;
    }
}
