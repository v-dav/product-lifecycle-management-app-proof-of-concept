package plm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plm.services.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller for managing Document entities
 * in the Product Lifecycle Management (PLM) system.
 */
@RestController
@RequestMapping("/Document")
public class DocumentController extends AbstractController {

    /**
     * Sets the DocumentService for this controller.
     *
     * @param documentService The DocumentService to set.
     */
    @Autowired
    public void setService(DocumentService documentService) {
        this.service = documentService;
    }
}