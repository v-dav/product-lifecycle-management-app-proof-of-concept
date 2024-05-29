package plm.dao;

/**
 * Interface defining the operations for managing entities in the PLM system.
 */
public interface Dao {

    /**
     * Retrieves an entity based on its reference, version, and iteration.
     *
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     * @return The entity.
     */
    Object get(String reference, String version, int iteration);

    /**
     * Creates a new entity in the database.
     *
     * @param entity The entity to create.
     */
    void create(Object entity);

    /**
     * Updates the given entity in the database.
     *
     * @param entity The entity to update.
     */
    void update(Object entity);
}