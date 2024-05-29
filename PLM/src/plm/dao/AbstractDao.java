package plm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract Data Access Object (DAO) class providing common CRUD operations.
 */
@Transactional
public abstract class AbstractDao implements Dao {

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Object get(String reference, String version, int iteration);

    /**
     * {@inheritDoc}
     */
    @Override
    public void create(Object entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(entity);
        currentSession.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(Object entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(entity);
        currentSession.flush();
    }
}