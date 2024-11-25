package com.kryprforge.database.dao;

import com.kryprforge.database.repository.DeliveryHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class DeliveryHistoryDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(DeliveryHistory deliveryHistory) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(deliveryHistory);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public DeliveryHistory findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(DeliveryHistory.class, id);
        } finally {
            em.close();
        }
    }

    public List<DeliveryHistory> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT dh FROM DeliveryHistory dh", DeliveryHistory.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(DeliveryHistory deliveryHistory) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(deliveryHistory);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            DeliveryHistory deliveryHistory = em.find(DeliveryHistory.class, id);
            if (deliveryHistory != null) {
                em.remove(deliveryHistory);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
