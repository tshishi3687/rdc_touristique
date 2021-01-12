package com.example.rdc_touristique.test;

import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.dto.VilleDTO;
import com.example.rdc_touristique.business.service.ProvinceService;
import com.example.rdc_touristique.business.service.VilleService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import com.example.rdc_touristique.exeption.ProvinceFoundExeption;
import com.example.rdc_touristique.exeption.VilleFoundExeption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.rdc_touristique")
public class VilleTest {

    public static void main(String[] args) throws ElementAlreadyExistsException, ProvinceFoundExeption, VilleFoundExeption {

        ApplicationContext ctx = SpringApplication.run(VilleTest.class, args);
        VilleService serv = ctx.getBean(VilleService.class);


        ProvinceDTO pro = new ProvinceDTO(
                1,
                "Bas-Uele","Formant la partie ouest de l'Uélé, ou l'Uélé occidental, le Bas-Uele est un espace faiblement peuplé et sa population, fortement dispersée, vit au milieu de ressources naturelles convoitées. "
        );



//        serv.creat(ville);
    }
}
