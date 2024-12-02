package com.kryprforge.dao;

import com.kryprforge.models.Promotion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PromotionDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(Promotion promotion) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(promotion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Promotion findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Promotion.class, id);
        } finally {
            em.close();
        }
    }

    public List<Promotion> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Promotion p", Promotion.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Promotion promotion) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(promotion);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Promotion promotion = em.find(Promotion.class, id);
            if (promotion != null) {
                em.remove(promotion);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
