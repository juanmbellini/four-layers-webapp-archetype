package ${package}.persistence;


import ${package}.interfaces.SampleDao;
import ${package}.models.SampleModel;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


/**
 * A sample dao using Hibernate
 */
@Repository
public class SampleHibernateDao implements SampleDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<SampleModel> getAll() {
        return em.createQuery("FROM SampleModel", SampleModel.class).getResultList();
    }

    @Override
    public Optional<SampleModel> getById(long id) {
        final List<SampleModel> result = em.createQuery("FROM SampleModel WHERE id = :id")
                .setParameter("id", id)
                .getResultList();
        return Optional.ofNullable(result.isEmpty() ? null : result.get(0));
    }

    @Override
    public SampleModel create(String name) {
        final SampleModel sampleModel = new SampleModel(name);
        em.persist(sampleModel);
        return sampleModel;

    }

    @Override
    public void update(SampleModel sampleModel, String name) {
        if (sampleModel == null) {
            throw new IllegalArgumentException("The entity must not be null");
        }
        sampleModel.update(name);
        em.merge(sampleModel);
    }

    @Override
    public void delete(SampleModel sampleModel) {
        if (sampleModel == null) {
            throw new IllegalArgumentException("The entity must not be null");
        }
        em.remove(sampleModel);
    }
}