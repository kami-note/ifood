package com.kryprforge;

import com.kryprforge.database.repository.Address;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Address address = new Address(
                "Main Street",
                "123",
                "Downtown",
                "Springfield",
                "SP",
                "12345-678",
                "Residential"
        );

        em.persist(address);

        em.getTransaction().commit();
        em.close();
        emf.close();

        System.out.println("Address saved successfully!");
    }
}
