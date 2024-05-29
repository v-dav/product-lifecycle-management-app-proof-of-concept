package plm.test;

import plm.model.LifeCycleTemplate;
import plm.model.Part;
import plm.model.Document;
import plm.model.VersionSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages",
            Locale.ENGLISH);

    public static void main(String[] args) {
        // Create instances of dependent objects
        LifeCycleTemplate lifeCycleTemplate = new LifeCycleTemplate();
        VersionSchema versionSchema = new VersionSchema();

        System.out.println("-----------------------Testing the Part object methods-----------------------");

        Part part = new Part("REF001", "1.0", 1);

        // Test valid setters
        try {
            part.setReservedBy("user1")
                    .setLifeCycleTemplate(lifeCycleTemplate)
                    .setLifeCycleState("In Progress")
                    .setVersionSchema(versionSchema);

            part.setPartAttribute1("Attribute1").setPartAttribute2("Attribute2");

            logger.info(bundle.getString("part.reference") + ": " + part.getReference());
            logger.info(bundle.getString("part.version") + ": " + part.getVersion());
            logger.info(bundle.getString("part.iteration") + ": " + part.getIteration());
            logger.info(bundle.getString("partAttribute1") + ": " + part.getPartAttribute1());
            logger.info(bundle.getString("partAttribute2") + ": " + part.getPartAttribute2());
            logger.info("Valid setters executed successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed for valid setters: {}", e.getMessage());
        }

        // Test invalid setters
        try {
            part.setReservedBy("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidReservedBy"), e.getMessage());
        }

        try {
            part.setLifeCycleTemplate(null);
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.nullLifeCycleTemplate"), e.getMessage());
        }

        try {
            part.setLifeCycleState("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidLifeCycleState"), e.getMessage());
        }

        try {
            part.setVersionSchema(null);
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.nullVersionSchema"), e.getMessage());
        }

        try {
            part.setPartAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidPartAttribute1"), e.getMessage());
        }

        try {
            part.setPartAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidPartAttribute2"), e.getMessage());
        }

        System.out.println("-----------------------Testing the Document object methods-----------------------");

        Document document = new Document("DOC001", "1.0", 1);

        // Test valid setters
        try {
            document.setReservedBy("user2")
                    .setLifeCycleTemplate(lifeCycleTemplate)
                    .setLifeCycleState("Draft")
                    .setVersionSchema(versionSchema);

            document.setDocumentAttribute1("DocumentAttr1").setDocumentAttribute2("DocumentAttr2");

            logger.info(bundle.getString("part.reference") + ": " + document.getReference());
            logger.info(bundle.getString("part.version") + ": " + document.getVersion());
            logger.info(bundle.getString("part.iteration") + ": " + document.getIteration());
            logger.info(bundle.getString("partAttribute1") + ": " + document.getPartAttribute1());
            logger.info(bundle.getString("partAttribute2") + ": " + document.getPartAttribute2());
            logger.info(bundle.getString("document.attribute1") + ": " + document.getDocumentAttribute1());
            logger.info(bundle.getString("document.attribute2") + ": " + document.getDocumentAttribute2());
            logger.info("Valid setters executed successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed for valid setters: {}", e.getMessage());
        }

        // Test invalid setters
        try {
            document.setReservedBy("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidReservedBy"), e.getMessage());
        }

        try {
            document.setLifeCycleTemplate(null);
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.nullLifeCycleTemplate"), e.getMessage());
        }

        try {
            document.setLifeCycleState("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidLifeCycleState"), e.getMessage());
        }

        try {
            document.setVersionSchema(null);
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.nullVersionSchema"), e.getMessage());
        }

        try {
            document.setPartAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidPartAttribute1"), e.getMessage());
        }

        try {
            document.setPartAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidPartAttribute2"), e.getMessage());
        }

        try {
            document.setDocumentAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidDocumentAttribute1"), e.getMessage());
        }

        try {
            document.setDocumentAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error(bundle.getString("error.invalidDocumentAttribute2"), e.getMessage());
        }
    }
}