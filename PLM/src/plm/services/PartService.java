package plm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plm.dao.DocumentDao;
import plm.dao.PartDao;
import plm.model.Document;
import plm.model.Part;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Service class for managing Part entities in the Product Lifecycle Management (PLM) system.
 * Implements the Service interface to provide CRUD operations and lifecycle management for parts.
 */
@Service
public class PartService implements plm.services.Service {

    @Autowired
    private PartDao partDao;

    @Autowired
    private DocumentDao documentDao;

    private static final Logger logger = LoggerFactory.getLogger(PartService.class);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages",
            Locale.ENGLISH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void reserve(String userId, String reference, String version, int iteration) {
        Part part = partDao.get(reference, version, iteration);

        if (!part.isReserved() && !part.getLifeCycleTemplate().isFinal(part.getLifeCycleState())) {
            Part nextPartIteration = new Part(part.getReference(), part.getVersion(),
                    iteration + 1);

            nextPartIteration.setReserved(true)
                    .setReservedBy(userId)
                    .setLifeCycleTemplate(part.getLifeCycleTemplate())
                    .setLifeCycleState(part.getLifeCycleState())
                    .setVersionSchema(part.getVersionSchema());

            nextPartIteration
                    .setPartAttribute1(part.getPartAttribute1())
                    .setPartAttribute2(part.getPartAttribute2());

            partDao.create(nextPartIteration);

            for (Document document : getLinkedDocuments(part)) {
                Document nextIteration = new Document(document.getReference(),
                        document.getVersion(), iteration + 1);

                nextIteration.setReserved(true)
                        .setReservedBy(userId)
                        .setLifeCycleTemplate(document.getLifeCycleTemplate())
                        .setLifeCycleState(document.getLifeCycleState())
                        .setVersionSchema(document.getVersionSchema());

                nextIteration
                        .setDocumentAttribute1(document.getDocumentAttribute1())
                        .setDocumentAttribute2(document.getDocumentAttribute2());

                documentDao.create(nextIteration);
            }
        } else {
            logger.error(bundle.getString("error.PartCannotReserve"));
            throw new IllegalArgumentException(bundle.getString("error.PartCannotReserve"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String userId, String reference, String version,
                       int iteration, String partAttribute1, String partAttribute2) {
        Part part = partDao.get(reference, version, iteration);

        if (part.isReserved() && part.getReservedBy().equals(userId)) {
            part.setPartAttribute1(partAttribute1).setPartAttribute2(partAttribute2);

            partDao.update(part);
        } else {
            logger.error(bundle.getString("error.PartCannotUpdate"));
            throw new IllegalArgumentException(bundle.getString("error.PartCannotUpdate"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void free(String userId, String reference, String version, int iteration) {
        Part part = partDao.get(reference, version, iteration);

        if (part.isReserved() && part.getReservedBy().equals(userId)) {
            part.setReserved(false).setReservedBy(null);
            partDao.update(part);

            for (Document document : getLinkedDocuments(part)) {
                document.setReserved(false).setReservedBy(null);
                documentDao.update(document);
            }
        } else {
            logger.error(bundle.getString("error.PartCannotFree"));
            throw new IllegalArgumentException(bundle.getString("error.PartCannotFree"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(String userId, String reference, String version,
                         int iteration, String state) {
        Part part = partDao.get(reference, version, iteration);

        if (!part.isReserved() && part.getLifeCycleTemplate().isKnown(state)) {
            part.setLifeCycleState(state);
            partDao.update(part);

            for (Document document : getLinkedDocuments(part)) {
                document.setLifeCycleState(state);
                documentDao.update(document);
            }
        } else {
            logger.error(bundle.getString("error.PartCannotSetState"));
            throw new IllegalArgumentException(bundle.getString("error.PartCannotSetState"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void revise(String userId, String reference, String version, int iteration) {
        Part part = partDao.get(reference, version, iteration);

        if (!part.isReserved() && part.getLifeCycleTemplate().isFinal(part.getLifeCycleState())) {
            Part nextPartVersion = new Part(part.getReference(),
                    part.getVersionSchema().getNextVersionLabel(version), 1);

            nextPartVersion.setReserved(false)
                    .setReservedBy(null)
                    .setLifeCycleTemplate(part.getLifeCycleTemplate())
                    .setLifeCycleState(part.getLifeCycleTemplate().getInitialState())
                    .setVersionSchema(part.getVersionSchema());

            nextPartVersion
                    .setPartAttribute1(part.getPartAttribute1())
                    .setPartAttribute2(part.getPartAttribute2());

            partDao.create(nextPartVersion);

            for (Document document : getLinkedDocuments(part)) {
                Document nextDocumentVersion = new Document(document.getReference(),
                        document.getVersionSchema().getNextVersionLabel(version), 1);

                nextDocumentVersion.setReserved(false)
                        .setReservedBy(null)
                        .setLifeCycleTemplate(document.getLifeCycleTemplate())
                        .setLifeCycleState(document.getLifeCycleTemplate().getInitialState())
                        .setVersionSchema(document.getVersionSchema());

                nextDocumentVersion
                        .setDocumentAttribute1(document.getDocumentAttribute1())
                        .setDocumentAttribute2(document.getDocumentAttribute2());

                documentDao.create(nextDocumentVersion);
            }
        } else {
            logger.error(bundle.getString("error.PartCannotRevise"));
            throw new IllegalArgumentException(bundle.getString("error.PartCannotRevise"));
        }
    }

    /**
     * Retrieves the set of documents linked to a given Part entity.
     *
     * @param part The part entity for which to retrieve linked documents.
     * @return A set of documents linked to the specified part.
     */
    private Set<Document> getLinkedDocuments(Part part) {
        // Implementation and returned value are not relevant for this exercise
        return null;
    }
}