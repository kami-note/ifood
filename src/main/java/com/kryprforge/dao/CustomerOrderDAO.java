package com.kryprforge.dao;

import com.kryprforge.models.CustomerOrder;
import com.kryprforge.models.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class CustomerOrderDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public Long save(CustomerOrder customerOrder) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customerOrder);
            em.getTransaction().commit();
            return customerOrder.getId();
        } finally {
            em.close();
        }
    }

    public CustomerOrder findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(CustomerOrder.class, id);
        } finally {
            em.close();
        }
    }

    public List<CustomerOrder> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT co FROM CustomerOrder co", CustomerOrder.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(CustomerOrder customerOrder) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(customerOrder);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            CustomerOrder customerOrder = em.find(CustomerOrder.class, id);
            if (customerOrder != null) {
                em.remove(customerOrder);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<CustomerOrder> findAllActiveOrders() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT co FROM CustomerOrder co WHERE co.status = :status", CustomerOrder.class)
                    .setParameter("status", Status.ACTIVE)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
