package com.kryprforge.dao;

import com.kryprforge.models.PaymentMethod;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PaymentMethodDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(PaymentMethod paymentMethod) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(paymentMethod);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public PaymentMethod findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(PaymentMethod.class, id);
        } finally {
            em.close();
        }
    }

    public List<PaymentMethod> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT pm FROM PaymentMethod pm", PaymentMethod.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(PaymentMethod paymentMethod) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(paymentMethod);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            PaymentMethod paymentMethod = em.find(PaymentMethod.class, id);
            if (paymentMethod != null) {
                em.remove(paymentMethod);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
