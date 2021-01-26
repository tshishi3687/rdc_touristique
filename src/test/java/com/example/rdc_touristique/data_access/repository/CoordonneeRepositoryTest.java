package com.example.rdc_touristique.data_access.repository;

import com.example.rdc_touristique.data_access.entity.Coordonnee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJdbcTest
class CoordonneeRepositoryTest {
    @Autowired
    private CoordonneeRepository coordaoRepository;

    @Test
    void creat(){
        Coordonnee coordonnee = new Coordonnee();
        coordonnee.setVille(null);
        coordonnee.setNum(5);
        coordonnee.setEmail("jkhjkhjkhkjhghk");
        coordonnee.setCpostal(5555);
        coordonnee.setRue("jkghjghjghjghjg");

        coordaoRepository.save(coordonnee);
        coordaoRepository.getOne(12);
    }
}

//    @Autowired
//    private TestEntityManager em;
//    @Autowired
//    private EmployeeRepository repo;
//    @Test
//    void findByName(){​​​​​
//        Employee expected = Employee.builder()
//                .name("test")
//                .salary(2000)
//                .build();
//        em.persist(expected);
//        em.flush();
//        Employee actual = repo.findByName("test");
//        assertEquals(expected, actual);
//    }​​​​​
