package plm.test;

import plm.model.LifeCycleTemplate;
import plm.model.Part;
import plm.model.Document;
import plm.model.VersionSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

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

            logger.info("Valid setters executed successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed for valid setters: {}", e.getMessage());
        }

        // Test invalid setters
        try {
            part.setReservedBy("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for reservedBy: {}", e.getMessage());
        }

        try {
            part.setLifeCycleTemplate(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for lifeCycleTemplate: {}", e.getMessage());
        }

        try {
            part.setLifeCycleState("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for lifeCycleState: {}", e.getMessage());
        }

        try {
            part.setVersionSchema(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for versionSchema: {}", e.getMessage());
        }

        try {
            part.setPartAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for partAttribute1: {}", e.getMessage());
        }

        try {
            part.setPartAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for partAttribute2: {}", e.getMessage());
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

            logger.info("Valid setters executed successfully");
        } catch (IllegalArgumentException e) {
            logger.error("Validation failed for valid setters: {}", e.getMessage());
        }

        // Test invalid setters
        try {
            document.setReservedBy("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for reservedBy: {}", e.getMessage());
        }

        try {
            document.setLifeCycleTemplate(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for lifeCycleTemplate: {}", e.getMessage());
        }

        try {
            document.setLifeCycleState("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for lifeCycleState: {}", e.getMessage());
        }

        try {
            document.setVersionSchema(null);
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for versionSchema: {}", e.getMessage());
        }

        try {
            document.setPartAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for partAttribute1: {}", e.getMessage());
        }

        try {
            document.setPartAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for partAttribute2: {}", e.getMessage());
        }

        try {
            document.setDocumentAttribute1("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for documentAttribute1: {}", e.getMessage());
        }

        try {
            document.setDocumentAttribute2("");
        } catch (IllegalArgumentException e) {
            logger.error("Caught expected exception for documentAttribute2: {}", e.getMessage());
        }
    }
}