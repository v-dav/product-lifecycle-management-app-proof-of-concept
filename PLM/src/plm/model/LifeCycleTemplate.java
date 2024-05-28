package plm.model;

import javax.persistence.Entity;

/**
 * Entity class representing the lifecycle template in the Product Lifecycle Management (PLM) system.
 * This class is used to manage the lifecycle states of parts and documents.
 *
 * @Entity specifies that the class is an entity and is mapped to a database table.
 */
@Entity
public class LifeCycleTemplate {

    /**
     * Gets the initial lifecycle state.
     *
     * @return The initial lifecycle state.
     */
    public String getInitialState() {
        // Implementation and returned value are not relevant for this exercise
        return null;
    }

    /**
     * Checks if the given lifecycle state is known.
     *
     * @param lifeCycleState The lifecycle state to check.
     * @return true if the lifecycle state is known, false otherwise.
     */
    public boolean isKnown(String lifeCycleState) {
        // Implementation and returned value are not relevant for this exercise
        return true;
    }

    /**
     * Checks if the given lifecycle state is a final state.
     *
     * @param lifeCycleState The lifecycle state to check.
     * @return true if the lifecycle state is a final state, false otherwise.
     */
    public boolean isFinal(String lifeCycleState) {
        // Implementation and returned value are not relevant for this exercise
        return true;
    }
}