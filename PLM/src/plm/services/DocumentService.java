package plm.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plm.dao.DocumentDao;
import plm.model.Document;

import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class DocumentService {

    @Autowired
    private DocumentDao documentDao;

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages",
            Locale.ENGLISH);

    /**
     * Reserves a Document entity and creates a new iteration.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
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
     * Updates a Document entity's attributes if it is reserved by the given user.
     *
     * @param userId             The user ID making the request.
     * @param reference          The reference of the document.
     * @param version            The version of the document.
     * @param iteration          The iteration of the document.
     * @param documentAttribute1 The first attribute of the document.
     * @param documentAttribute2 The second attribute of the document.
     */
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
     * Frees a reserved Document entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
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
     * Sets the state of a Document entity if it is not reserved.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     * @param state     The new state of the document.
     */
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
     * Revises a Document entity by creating a new version.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
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