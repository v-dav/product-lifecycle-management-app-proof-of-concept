package plm.services;

/**
 * Interface defining the operations for managing entities in the PLM system.
 */
public interface Service {

    /**
     * Reserves an entity and creates a new iteration.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     */
    void reserve(String userId, String reference, String version, int iteration);

    /**
     * Updates an entity's attributes if it is reserved by the given user.
     *
     * @param userId     The user ID making the request.
     * @param reference  The reference of the entity.
     * @param version    The version of the entity.
     * @param iteration  The iteration of the entity.
     * @param attribute1 The first attribute of the entity.
     * @param attribute2 The second attribute of the entity.
     */
    void update(String userId, String reference, String version, int iteration,
                String attribute1, String attribute2);

    /**
     * Frees a reserved entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     */
    void free(String userId, String reference, String version, int iteration);

    /**
     * Sets the state of an entity if it is not reserved.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     * @param state     The new state of the entity.
     */
    void setState(String userId, String reference, String version, int iteration, String state);

    /**
     * Revises an entity by creating a new version.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     */
    void revise(String userId, String reference, String version, int iteration);
}