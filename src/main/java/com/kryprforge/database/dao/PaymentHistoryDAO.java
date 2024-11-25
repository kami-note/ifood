package com.kryprforge.database.dao;

import com.kryprforge.database.repository.PaymentHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PaymentHistoryDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(PaymentHistory paymentHistory) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(paymentHistory);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public PaymentHistory findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(PaymentHistory.class, id);
        } finally {
            em.close();
        }
    }

    public List<PaymentHistory> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT ph FROM PaymentHistory ph", PaymentHistory.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(PaymentHistory paymentHistory) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(paymentHistory);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            PaymentHistory paymentHistory = em.find(PaymentHistory.class, id);
            if (paymentHistory != null) {
                em.remove(paymentHistory);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
