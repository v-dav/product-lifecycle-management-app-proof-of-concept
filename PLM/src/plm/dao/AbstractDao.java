package plm.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

// Abstract Data Access Object (DAO) class providing common CRUD operations.
@Transactional
public abstract class AbstractDao {

    @Autowired
    protected SessionFactory sessionFactory;

    /**
     * Retrieves an entity based on its reference, version, and iteration.
     *
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     * @return The entity.
     */
    public abstract Object get(String reference, String version, int iteration);

    /**
     * Creates a new entity in the database.
     *
     * @param entity The entity to create.
     */
    public void create(Object entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(entity);
        currentSession.flush();
    }

    /**
     * Updates the given entity in the database.
     *
     * @param entity The entity to update.
     */
    public void update(Object entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.update(entity);
        currentSession.flush();
    }
}