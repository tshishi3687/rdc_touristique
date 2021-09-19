package com.example.rdc_touristique.business.mapper;

import com.example.rdc_touristique.business.dto.ActionDTO;
import com.example.rdc_touristique.business.dto.PersonneSimplifierDTO;
import com.example.rdc_touristique.data_access.entity.Action;
import com.example.rdc_touristique.data_access.entity.Personne;
import com.example.rdc_touristique.data_access.repository.PersonneReposytory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Component
public class ActionMapper implements Mapper<ActionDTO, Action> {

    @Override

    public ActionDTO toDTO(Action action) {

        if (action==null)
            return null;

        return new ActionDTO(
                action.getId(),
                action.getDate(),
                action.getClassName(),
                action.getIdClasse(),
                action.getAction(),
                action.getDescription()
        );
    }

    @Override
    public Action toEntity(ActionDTO actionDTO) throws NoSuchAlgorithmException, InvalidKeySpecException {

        if (actionDTO==null)
            return null;
        Action action = new Action();
        action.setId(actionDTO.getId());
        action.setDate(actionDTO.getDate());
        action.setClassName(actionDTO.getClassName());
        action.setIdClasse(actionDTO.getIdClasse());
        action.setAction(actionDTO.getAction());
        action.setDescription(actionDTO.getDescription());

        return action;
    }
}
