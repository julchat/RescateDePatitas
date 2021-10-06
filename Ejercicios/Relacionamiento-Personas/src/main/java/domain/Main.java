package domain;

import database.EntityManagerHelper;

import javax.persistence.EntityManager;

public class Main {

    public static void main(String[] args) {

        EntityManager em = EntityManagerHelper.getEntityManager();
    }
}
