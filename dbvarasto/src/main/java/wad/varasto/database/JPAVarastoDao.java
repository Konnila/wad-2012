package wad.varasto.database;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import wad.varasto.domain.Esine;

@Repository
public class JPAVarastoDao implements VarastoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Esine instance) {
        entityManager.merge(instance);
    }

    @Override
    public Esine read(int id) {
        return entityManager.find(Esine.class, id);
    }

    @Override
    public void delete(Esine instance) {
        entityManager.remove(instance);
    }

    @Override
    public Esine update(Esine instance) {
        return entityManager.merge(instance);
    }

    @Override
    public List<Esine> list() {
        Query q = entityManager.createQuery("SELECT e FROM Esine e");
        return q.getResultList();
    }
}
