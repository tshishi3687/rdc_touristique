package com.example.rdc_touristique.test;

import com.example.rdc_touristique.business.dto.*;
import com.example.rdc_touristique.business.service.BienService;
import com.example.rdc_touristique.business.service.CoordonneeService;
import com.example.rdc_touristique.data_access.entity.Type_bien;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BientTest {
    public static void main(String[] args) throws ElementAlreadyExistsException {

        ApplicationContext ctx = SpringApplication.run(BientTest.class, args);
        BienService serv = ctx.getBean(BienService.class);

        ProvinceDTO province = new ProvinceDTO(
                38,
                "Bas-Uele",
                "Formant la partie ouest de l'Uélé, ou l'Uélé occidental, le Bas-Uele est un espace faiblement peuplé et sa population, fortement dispersée, vit au milieu de ressources naturelles convoitées. Au Bas-Uele, le facteur humain (autrement dit, la main-d'œuvre potentielle) est indissociable des ressources naturelles dont il est partie intégrante2."
        );

        VilleDTO ville = new VilleDTO(
                47,
                "Buta",
                1000,
                province,
                "Buta est le chef-lieu de la province du Bas-Uele en République démocratique du Congo depuis le démembrement de l'ex Province Orientale. Elle s’étend sur les deux rives de la boucle tracée en cet endroit par la rivière Rubi, cour supérieur de l'Itimbiri."
        );

        CoordonneeDTO coordonnee = new CoordonneeDTO(
                0,
                ville,
                5555,
                "ddddddd",
                55,
                "dddddddd",
                0555555555
        );

        Type_bienDTO type_bienDTO = new Type_bienDTO(
                1,
                "Maison"
        );

        LocalDate date = LocalDate.now();
        PersonneSimplifierDTO personneSimpleDTO = new PersonneSimplifierDTO(
                1,
                "ced",
                "tshishi"

        );

//        BienDTO bienDTO = new BienDTO(
//            0,
//            type_bienDTO,
//            222,
//                2,
//                2,
//                2,
//                2,
//                2,
//                2,
//                "fffff",
//                "gggggggggggg",
//                coordonnee,
//                personneSimpleDTO
//        );
//        serv.creat(bienDTO);
//    }
    }
}
