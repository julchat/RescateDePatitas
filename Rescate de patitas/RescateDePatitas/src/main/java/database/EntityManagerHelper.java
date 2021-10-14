package database;

import domain.business.mascota.Mascota;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerHelper {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("rescate-patitas");
    }

    public static EntityManager getEntityManager() {
        EntityManager em = factory.createEntityManager();
        return em;
    }

    public static void comenzarTransaccion(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (!tx.isActive()) {
            tx.begin();
        }
    }

    public static void commit(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.commit();
        }
    }

    public static void rollback(EntityManager em) {
        EntityTransaction tx = em.getTransaction();
        if (tx.isActive()) {
            tx.rollback();
        }
    }
}