package plm.model;

/**
 * Interface defining common behaviors for
 * Product Lifecycle Management (PLM) system entities.
 */
public interface Entity {

    // Getters

    /**
     * Checks if the entity is reserved.
     *
     * @return true if the entity is reserved, false otherwise.
     */
    boolean isReserved();

    /**
     * Gets the user who reserved the entity.
     *
     * @return The user who reserved the entity.
     */
    String getReservedBy();

    /**
     * Gets the lifecycle template of the entity.
     *
     * @return The lifecycle template of the entity.
     */
    LifeCycleTemplate getLifeCycleTemplate();

    /**
     * Gets the lifecycle state of the entity.
     *
     * @return The lifecycle state of the entity.
     */
    String getLifeCycleState();

    /**
     * Gets the version schema of the entity.
     *
     * @return The version schema of the entity.
     */
    VersionSchema getVersionSchema();

    // Setters

    /**
     * Sets the reserved status of the entity.
     *
     * @param reserved The new reserved status.
     * @return The current instance of the entity for method chaining.
     */
    Entity setReserved(boolean reserved);

    /**
     * Sets the user who reserved the entity.
     *
     * @param reservedBy The user who reserved the entity.
     * @return The current instance of the entity for method chaining.
     */
    Entity setReservedBy(String reservedBy);

    /**
     * Sets the lifecycle template of the entity.
     *
     * @param lifeCycleTemplate The new lifecycle template.
     * @return The current instance of the entity for method chaining.
     */
    Entity setLifeCycleTemplate(LifeCycleTemplate lifeCycleTemplate);

    /**
     * Sets the lifecycle state of the entity.
     *
     * @param lifeCycleState The new lifecycle state.
     * @return The current instance of the entity for method chaining.
     */
    Entity setLifeCycleState(String lifeCycleState);

    /**
     * Sets the version schema of the entity.
     *
     * @param versionSchema The new version schema.
     * @return The current instance of the entity for method chaining.
     */
    Entity setVersionSchema(VersionSchema versionSchema);
}