package plm.model;

import javax.persistence.Entity;

/**
 * Entity class representing the version schema in the Product Lifecycle Management (PLM) system.
 * This class is used to determine the next version label based on the current version label.
 *
 * @Entity specifies that the class is an entity and is mapped to a database table.
 */
@Entity
public class VersionSchema {

    /**
     * Gets the next version label based on the current version label.
     *
     * @param currentVersionLabel The current version label.
     * @return The next version label.
     */
    public String getNextVersionLabel(String currentVersionLabel) {
        // Implementation and returned value are not relevant for this exercise
        return null;
    }
}