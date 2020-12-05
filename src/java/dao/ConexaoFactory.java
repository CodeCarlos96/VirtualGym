package dao;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoFactory {

    private static EntityManagerFactory emf = null;

    public EntityManager getConexao() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("VirtualGymPU");
        }
        return emf.createEntityManager();
    }

    @PreDestroy
    public void destruct() {
        emf.close();
    }

}
