package com.example.rdc_touristique.test;

import com.example.rdc_touristique.business.dto.ProvinceDTO;
import com.example.rdc_touristique.business.service.ProvinceService;
import com.example.rdc_touristique.exeption.ElementAlreadyExistsException;
import com.example.rdc_touristique.exeption.FoundExeption;
import com.example.rdc_touristique.exeption.ProvinceFoundExeption;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.util.AutoPopulatingList;

//@SpringBootApplication
//@ComponentScan("com.example.rdc_touristique")
public class ProvinceTest {

  public static void main(String[] args) throws ElementAlreadyExistsException {

      ApplicationContext ctx = SpringApplication.run(ProvinceTest.class, args);
      ProvinceService serv = ctx.getBean(ProvinceService.class);



      ProvinceDTO pro = new ProvinceDTO(
              0,
              "Equateur",
              "La province de l'Équateur est depuis 2015 une province de la république démocratique du Congo à la suite de l'éclatement de la province historique de l'Équateur2. Les principaux centres urbains sont Lukolela, Bikoro, Basankusu, Bolomba, Bomongo, Irebu, Makanza et la ville de Mbandaka. "
      );

      // test Creat
//        serv.creat(pro);

      // test readOne -
//        try{
//			System.out.println( serv.readOne(1) );
//		}
//		catch (ProvinceFoundExeption e){
//			System.out.println(e.getMessage());
//		}

      // read all
      System.out.println(serv.readAll());
      // test update - V

//		try{
//			serv.update( pro );
//		}
//		catch (FoundExeption e){
//			System.out.println( e.getMessage() );
//		}

//        // test delete -

//      try{
//          serv.delete(2);
//      }
//      catch (ProvinceFoundExeption e)
//      {
//          System.out.println(e.getMessage());
//      }
  }

}
