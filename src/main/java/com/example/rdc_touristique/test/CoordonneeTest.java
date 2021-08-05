package com.example.rdc_touristique.test;

import com.example.rdc_touristique.business.dto.CoordonneeDTO;
import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.service.CoordonneeService;
import com.example.rdc_touristique.data_access.entity.Coordonnee;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@SpringBootApplication
@ComponentScan("com.example.rdc_touristique")
public class CoordonneeTest {

    public static void main(String[] args) throws ElementAlreadyExistsException, NoSuchAlgorithmException, InvalidKeySpecException {

        ApplicationContext ctx = SpringApplication.run(CoordonneeTest.class, args);
        CoordonneeService serv = ctx.getBean(CoordonneeService.class);

//        ProvinceDTO province = new ProvinceDTO(
//                38,
//                "Bas-Uele",
//                "Formant la partie ouest de l'Uélé, ou l'Uélé occidental, le Bas-Uele est un espace faiblement peuplé et sa population, fortement dispersée, vit au milieu de ressources naturelles convoitées. Au Bas-Uele, le facteur humain (autrement dit, la main-d'œuvre potentielle) est indissociable des ressources naturelles dont il est partie intégrante2."
//        );

//        VilleDTO ville = new VilleDTO(
//                47,
//                "Buta",
//                1000,
//                province,
//                "Buta est le chef-lieu de la province du Bas-Uele en République démocratique du Congo depuis le démembrement de l'ex Province Orientale. Elle s’étend sur les deux rives de la boucle tracée en cet endroit par la rivière Rubi, cour supérieur de l'Itimbiri."
//        );

//        CoordonneeDTO coordonnee = new CoordonneeDTO(
//                0,
//                ville,
//                5555,
//                "ddddddd",
//                55,
//                "dddddddd",
//                0555555555
//        );
//
//        serv.creat(coordonnee);
    }
}
