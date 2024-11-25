package com.kryprforge.database.dao;

import com.kryprforge.database.repository.ProductAccompaniment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class ProductAccompanimentDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(ProductAccompaniment productAccompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(productAccompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public ProductAccompaniment findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(ProductAccompaniment.class, id);
        } finally {
            em.close();
        }
    }

    public List<ProductAccompaniment> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT pa FROM ProductAccompaniment pa", ProductAccompaniment.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(ProductAccompaniment productAccompaniment) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(productAccompaniment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            ProductAccompaniment productAccompaniment = em.find(ProductAccompaniment.class, id);
            if (productAccompaniment != null) {
                em.remove(productAccompaniment);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
