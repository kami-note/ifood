package com.kryprforge.database.dao;

import com.kryprforge.database.repository.OrderProductAccompaniment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class OrderProductAccompanimentDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(OrderProductAccompaniment orderProductAccompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(orderProductAccompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public OrderProductAccompaniment findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(OrderProductAccompaniment.class, id);
        } finally {
            em.close();
        }
    }

    public List<OrderProductAccompaniment> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT opa FROM OrderProductAccompaniment opa", OrderProductAccompaniment.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(OrderProductAccompaniment orderProductAccompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(orderProductAccompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            OrderProductAccompaniment orderProductAccompaniment = em.find(OrderProductAccompaniment.class, id);
            if (orderProductAccompaniment != null) {
                em.remove(orderProductAccompaniment);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
