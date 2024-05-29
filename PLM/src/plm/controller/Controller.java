package plm.controller;

/**
 * Interface defining common operations for managing entities in the
 * Product Lifecycle Management (PLM) system.
 */
public interface Controller {

    /**
     * Reserves an entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     */
    void reserve(String userId, String reference, String version, int iteration);

    /**
     * Updates an entity.
     *
     * @param userId             The user ID making the request.
     * @param reference          The reference of the entity.
     * @param version            The version of the entity.
     * @param iteration          The iteration of the entity.
     * @param attribute1 The first attribute to update.
     * @param attribute2 The second attribute to update.
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
     * Sets the state of an entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     * @param state     The new state of the entity.
     */
    void setState(String userId, String reference, String version, int iteration, String state);

    /**
     * Revises an entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the entity.
     * @param version   The version of the entity.
     * @param iteration The iteration of the entity.
     */
    void revise(String userId, String reference, String version, int iteration);
}