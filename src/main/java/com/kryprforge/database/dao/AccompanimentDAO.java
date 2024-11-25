package com.kryprforge.database.dao;

import com.kryprforge.database.repository.Accompaniment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AccompanimentDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(Accompaniment accompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(accompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Accompaniment findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Accompaniment.class, id);
        } finally {
            em.close();
        }
    }

    public List<Accompaniment> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Accompaniment a", Accompaniment.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Accompaniment accompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(accompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Accompaniment accompaniment = em.find(Accompaniment.class, id);
            if (accompaniment != null) {
                em.remove(accompaniment);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
