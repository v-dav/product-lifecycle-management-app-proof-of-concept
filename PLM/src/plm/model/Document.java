package plm.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entity class representing a Document in the Product Lifecycle Management (PLM) system.
 */
@Entity
@IdClass(plm.model.Document.DocumentPK.class)
public class Document extends Part {

    private static final Logger logger = LoggerFactory.getLogger(Document.class);

    @Column
    private String documentAttribute1;

    @Column
    private String documentAttribute2;

    // Constructors

    /**
     * Default constructor for Java Persistance API.
     */
    public Document() {
        // Default constructor
    }

    /**
     * Constructor to initialize the composite primary key fields.
     *
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
    public Document(String reference, String version, int iteration) {
        super(reference, version, iteration);
    }

    // Getters

    /**
     * Gets the first document attribute.
     *
     * @return The first document attribute.
     */
    public String getDocumentAttribute1() {
        return documentAttribute1;
    }

    /**
     * Gets the second document attribute.
     *
     * @return The second document attribute.
     */
    public String getDocumentAttribute2() {
        return documentAttribute2;
    }

    // Setters

    /**
     * Sets the first document attribute.
     *
     * @param documentAttribute1 The new first document attribute.
     * @return The current Document instance (for chaining).
     */
    public Document setDocumentAttribute1(String documentAttribute1) {
        if (documentAttribute1 == null || documentAttribute1.trim().isEmpty()) {
            logger.error("Attempted to set invalid documentAttribute1: {}", documentAttribute1);
            throw new IllegalArgumentException("Document attribute 1 cannot be blank");
        }
        this.documentAttribute1 = documentAttribute1;
        return this;
    }

    /**
     * Sets the second document attribute.
     *
     * @param documentAttribute2 The new second document attribute.
     * @return The current Document instance (for chaining).
     */
    public Document setDocumentAttribute2(String documentAttribute2) {
        if (documentAttribute2 == null || documentAttribute2.trim().isEmpty()) {
            logger.error("Attempted to set invalid documentAttribute2: {}", documentAttribute2);
            throw new IllegalArgumentException("Document attribute 2 cannot be blank");
        }
        this.documentAttribute2 = documentAttribute2;
        return this;
    }

    /**
     * Composite primary key class for the Document entity.
     */
    public static class DocumentPK extends Part.PartPK {
        /**
         * Default constructor for Java Persistance API.
         */
        public DocumentPK() {
            // Default constructor
        }

        /**
         * Constructor to initialize the composite key fields.
         *
         * @param reference The reference of the document.
         * @param version   The version of the document.
         * @param iteration The iteration of the document.
         */
        public DocumentPK(String reference, String version, int iteration) {
            super(reference, version, iteration);
        }

        /**
         * Generates a hash code for the DocumentPK instance.
         *
         * @return A hash code based on the reference, version, and iteration fields.
         */
        @Override
        public int hashCode() {
            return Objects.hash(getReference(), getVersion(), getIteration());
        }

        /**
         * Checks if two DocumentPK instances are equal.
         *
         * @param obj The object to compare with.
         * @return true if the objects are equal, false otherwise.
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            DocumentPK other = (DocumentPK) obj;
            return Objects.equals(getReference(), other.getReference()) &&
                    Objects.equals(getVersion(), other.getVersion()) &&
                    getIteration() == other.getIteration();
        }
    }
}