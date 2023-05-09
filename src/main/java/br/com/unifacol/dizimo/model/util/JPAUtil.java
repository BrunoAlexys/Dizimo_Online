package br.com.unifacol.dizimo.model.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("Dizimo_Online");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
