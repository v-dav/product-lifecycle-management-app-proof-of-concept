package plm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plm.dao.DocumentDao;
import plm.model.Document;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Service class for managing Document entities in the Product Lifecycle Management (PLM) system.
 * Implements the Service interface to provide CRUD operations and lifecycle management
 * for documents.
 */
@Service
public class DocumentService implements plm.services.Service {

    @Autowired
    private DocumentDao documentDao;

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages",
            Locale.ENGLISH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void reserve(String userId, String reference, String version, int iteration) {
        Document document = documentDao.get(reference, version, iteration);

        if (!document.isReserved() &&
                !document.getLifeCycleTemplate().isFinal(document.getLifeCycleState())) {
            Document nextDocumentIteration = new Document(document.getReference(),
                    document.getVersion(), iteration + 1);

            nextDocumentIteration.setReserved(true)
                    .setReservedBy(userId)
                    .setLifeCycleTemplate(document.getLifeCycleTemplate())
                    .setLifeCycleState(document.getLifeCycleState())
                    .setVersionSchema(document.getVersionSchema());

            nextDocumentIteration
                    .setDocumentAttribute1(document.getDocumentAttribute1())
                    .setDocumentAttribute2(document.getDocumentAttribute2());

            documentDao.create(nextDocumentIteration);
        } else {
            logger.error(bundle.getString("error.DocumentCannotReserve"));
            throw new IllegalArgumentException(bundle.getString("error.DocumentCannotReserve"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(String userId, String reference, String version, int iteration,
                       String documentAttribute1, String documentAttribute2) {
        Document document = documentDao.get(reference, version, iteration);

        if (document.isReserved() && document.getReservedBy().equals(userId)) {
            document.setDocumentAttribute1(documentAttribute1)
                    .setDocumentAttribute2(documentAttribute2);

            documentDao.update(document);
        } else {
            logger.error(bundle.getString("error.DocumentCannotUpdate"));
            throw new IllegalArgumentException(bundle.getString("error.DocumentCannotUpdate"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void free(String userId, String reference, String version, int iteration) {
        Document document = documentDao.get(reference, version, iteration);

        if (document.isReserved() && document.getReservedBy().equals(userId)) {
            document.setReserved(false).setReservedBy(null);
            documentDao.update(document);
        } else {
            logger.error(bundle.getString("error.DocumentCannotFree"));
            throw new IllegalArgumentException(bundle.getString("error.DocumentCannotFree"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setState(String userId, String reference, String version,
                         int iteration, String state) {
        Document document = documentDao.get(reference, version, iteration);

        if (!document.isReserved() && document.getLifeCycleTemplate().isKnown(state)) {
            document.setLifeCycleState(state);
            documentDao.update(document);
        } else {
            logger.error(bundle.getString("error.DocumentCannotSetState"));
            throw new IllegalArgumentException(bundle.getString("error.DocumentCannotSetState"));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void revise(String userId, String reference, String version, int iteration) {
        Document document = documentDao.get(reference, version, iteration);

        if (!document.isReserved() &&
                document.getLifeCycleTemplate().isFinal(document.getLifeCycleState())) {
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
        } else {
            logger.error(bundle.getString("error.DocumentCannotRevise"));
            throw new IllegalArgumentException(bundle.getString("error.DocumentCannotRevise"));
        }
    }
}