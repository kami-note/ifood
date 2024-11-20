package com.kryprforge.database.dao;

import com.kryprforge.database.repository.Address;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AddressDAO {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("defaultPU");

    public void save(Address address) {
        try (EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(address);
            em.getTransaction().commit();
        }
    }

    public Address findById(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.find(Address.class, id);
        } finally {
            em.close();
        }
    }

    public List<Address> findAll() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Address address) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(address);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        try {
            em.getTransaction().begin();
            Address address = em.find(Address.class, id);
            if (address != null) {
                em.remove(address);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    public static EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }
}
