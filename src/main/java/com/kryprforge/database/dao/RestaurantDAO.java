package com.kryprforge.database.dao;

import com.kryprforge.database.repository.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class RestaurantDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(Restaurant restaurant) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(restaurant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Restaurant findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Restaurant.class, id);
        } finally {
            em.close();
        }
    }

    public List<Restaurant> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Restaurant restaurant) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(restaurant);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Restaurant restaurant = em.find(Restaurant.class, id);
            if (restaurant != null) {
                em.remove(restaurant);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
