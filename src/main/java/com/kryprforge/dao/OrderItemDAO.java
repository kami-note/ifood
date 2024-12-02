package com.kryprforge.dao;

import com.kryprforge.models.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class OrderItemDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(OrderItem orderItem) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(orderItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public OrderItem findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(OrderItem.class, id);
        } finally {
            em.close();
        }
    }

    public List<OrderItem> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT oi FROM OrderItem oi", OrderItem.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(OrderItem orderItem) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(orderItem);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            OrderItem orderItem = em.find(OrderItem.class, id);
            if (orderItem != null) {
                em.remove(orderItem);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
