package com.example.rdc_touristique.business.DAO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class GenericDao {
    private static final String PERSISTENCE_UNIT_NAME = "persistence-config";
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    private static EntityManager em = emf.createEntityManager();

    public static void persist(Collection collection){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        for (Object serializable : collection) {
            em.persist(serializable);
        }
        tx.commit();
    }

    public static void persist(Serializable data){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(data);
        tx.commit();
    }

    @SuppressWarnings("rawtypes")
    public static List load(String className){
        Query q = em.createQuery("from " + className);
        return q.getResultList();
    }
}
