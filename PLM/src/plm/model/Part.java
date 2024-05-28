package plm.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity class representing a Part in the Product Lifecycle Management (PLM) system.
 *
 * @Entity specifies that the class is an entity and is mapped to a database table.
 * @IdClass(Part.PartPK.class) specifies a composite primary key class (PartPK) for the entity.
 */
@Entity
@IdClass(plm.model.Part.PartPK.class)
public class Part extends AbstractEntity {

    private static final Logger logger = LoggerFactory.getLogger(Part.class);

    // Fields

    /*
     * @Id annotations mark the fields that are part of the composite primary key.
     * @NotBlank enforces constraints on the fields.
     * @Min ensures it is a positive integer.
     */
    @Id
    @NotBlank(message = "Reference cannot be empty")
    private String reference;

    @Id
    @NotBlank(message = "Version cannot be empty")
    private String version;

    @Id
    @Min(value = 0, message = "Iteration must be a positive integer")
    private int iteration;

    @Column
    private String partAttribute1;

    @Column
    private String partAttribute2;

    // Constructors

    // Default constructor for Java Persistance API.
    public Part() {
        // Default constructor
    }

    /**
     * Constructor to initialize the composite primary key fields.
     *
     * @param reference The reference of the part.
     * @param version   The version of the part.
     * @param iteration The iteration of the part.
     */
    public Part(String reference, String version, int iteration) {
        this.reference = reference;
        this.version = version;
        this.iteration = iteration;
    }

    // Getters

    /**
     * Gets the reference of the part.
     *
     * @return The reference of the part.
     */
    public String getReference() {
        return reference;
    }

    /**
     * Gets the version of the part.
     *
     * @return The version of the part.
     */
    public String getVersion() {
        return version;
    }

    /**
     * Gets the iteration of the part.
     *
     * @return The iteration of the part.
     */
    public int getIteration() {
        return iteration;
    }

    /**
     * Gets the first part attribute.
     *
     * @return The first part attribute.
     */
    public String getPartAttribute1() {
        return partAttribute1;
    }

    /**
     * Gets the second part attribute.
     *
     * @return The second part attribute.
     */
    public String getPartAttribute2() {
        return partAttribute2;
    }

    // Setters

    /**
     * Sets the first part attribute.
     *
     * @param partAttribute1 The new first part attribute.
     */
    public Part setPartAttribute1(String partAttribute1) {
        if (partAttribute1 == null || partAttribute1.trim().isEmpty()) {
            logger.error("Attempted to set invalid partAttribute1: {}", partAttribute1);
            throw new IllegalArgumentException("Part attribute 1 cannot be blank");
        }
        this.partAttribute1 = partAttribute1;
        return this;
    }

    /**
     * Sets the second part attribute.
     *
     * @param partAttribute2 The new second part attribute.
     */
    public Part setPartAttribute2(String partAttribute2) {
        if (partAttribute2 == null || partAttribute2.trim().isEmpty()) {
            logger.error("Attempted to set invalid partAttribute2: {}", partAttribute2);
            throw new IllegalArgumentException("Part attribute 2 cannot be blank");
        }
        this.partAttribute2 = partAttribute2;
        return this;
    }

    // Composite primary key class for the Part entity.
    public static class PartPK {
        private String reference;
        private String version;
        private int iteration;

        // Default constructor for Java Persistance API.
        public PartPK() {
        }

        /**
         * Constructor to initialize the composite key fields.
         *
         * @param reference The reference of the part.
         * @param version   The version of the part.
         * @param iteration The iteration of the part.
         */
        public PartPK(String reference, String version, int iteration) {
            this.reference = reference;
            this.version = version;
            this.iteration = iteration;
        }

        // Getter methods for the fields
        public String getReference() {
            return reference;
        }

        public String getVersion() {
            return version;
        }

        public int getIteration() {
            return iteration;
        }

        /**
         * Generates a hash code for the PartPK instance.
         *
         * @return A hash code based on the reference, version, and iteration fields.
         */
        @Override
        public int hashCode() {
            return Objects.hash(iteration, reference, version);
        }

        /**
         * Checks if two PartPK instances are equal.
         *
         * @param obj The object to compare with.
         * @return true if the objects are equal, false otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            PartPK other = (PartPK) obj;
            return iteration == other.iteration && Objects.equals(reference, other.reference)
                    && Objects.equals(version, other.version);
        }
    }
}
