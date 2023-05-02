package br.com.unifacol.dizimo.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("Online_Tithe");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
