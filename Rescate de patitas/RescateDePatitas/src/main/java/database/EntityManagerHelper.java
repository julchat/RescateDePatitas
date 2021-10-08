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

    public static boolean usuarioDisponible(String nombreBuscado){
        EntityManager em = EntityManagerHelper.getEntityManager();

        String consultaUsuario = "'SELECT nombreUsuario FROM usuario WHERE nombreUsuario = " + nombreBuscado + "'";

        return em.createQuery(consultaUsuario).getResultList().isEmpty();
    }


    public static Mascota buscarMascota(int idBuscado) {
        EntityManager em = EntityManagerHelper.getEntityManager();

        String consultaMascota = "'SELECT * FROM mascota WHERE id = " + idBuscado + "'";

        return (Mascota) em.createQuery(consultaMascota).getResultList().get(0);
    }

}