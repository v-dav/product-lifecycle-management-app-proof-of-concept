package plm.model;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Abstract Entity class providing common implementation for PLM system entities.
 *
 * @MappedSuperclass indicates that it is a base class for JPA entities.
 */
@MappedSuperclass
public abstract class AbstractEntity implements Entity {
    /*
     * Component provided by SLF4J that allows to log messages
     * at different levels (e.g., info, debug, error).
     */
    private static final Logger logger = LoggerFactory.getLogger(AbstractEntity.class);

    //  Initializes the resource bundle based on the default locale.
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages",
            Locale.ENGLISH);

    // Fields

    // @Column annotations specify that these fields should be mapped to columns in the database.
    @Column
    private boolean reserved;

    @Column
    private String reservedBy;

    @Column
    private String lifeCycleState;

    /* @ManyToOne indicate many-to-one relationships with
     * LifeCycleTemplate and VersionSchema entities
     */
    @ManyToOne
    private LifeCycleTemplate lifeCycleTemplate;

    @ManyToOne
    private VersionSchema versionSchema;

    // Getters

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReserved() {
        return reserved;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getReservedBy() {
        return reservedBy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LifeCycleTemplate getLifeCycleTemplate() {
        return lifeCycleTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getLifeCycleState() {
        return lifeCycleState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VersionSchema getVersionSchema() {
        return versionSchema;
    }

    // Setters

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractEntity setReserved(boolean reserved) {
        this.reserved = reserved;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractEntity setReservedBy(String reservedBy) {
        if (reservedBy == null || reservedBy.trim().isEmpty()) {
            logger.error(bundle.getString("error.invalidReservedBy"), reservedBy);
            throw new IllegalArgumentException(bundle.getString("reservedBy") + " " +
                    bundle.getString("error.cannotBeBlank"));
        }
        this.reservedBy = reservedBy;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractEntity setLifeCycleTemplate(LifeCycleTemplate lifeCycleTemplate) {
        if (lifeCycleTemplate == null) {
            logger.error(bundle.getString("error.nullLifeCycleTemplate"));
            throw new IllegalArgumentException(bundle.getString("lifeCycleTemplate") + " " +
                    bundle.getString("error.cannotBeNull"));
        }
        this.lifeCycleTemplate = lifeCycleTemplate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractEntity setLifeCycleState(String lifeCycleState) {
        if (lifeCycleState == null || lifeCycleState.trim().isEmpty()) {
            logger.error(bundle.getString("error.invalidLifeCycleState"), lifeCycleState);
            throw new IllegalArgumentException(bundle.getString("lifeCycleState") + " " +
                    bundle.getString("error.cannotBeBlank"));
        }
        this.lifeCycleState = lifeCycleState;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractEntity setVersionSchema(VersionSchema versionSchema) {
        if (versionSchema == null) {
            logger.error(bundle.getString("error.nullVersionSchema"));
            throw new IllegalArgumentException(bundle.getString("versionSchema") + " " +
                    bundle.getString("error.cannotBeNull"));
        }
        this.versionSchema = versionSchema;
        return this;
    }
}