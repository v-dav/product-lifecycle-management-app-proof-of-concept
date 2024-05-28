package plm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import plm.services.DocumentService;

/**
 * Controller for managing Document entities in the Product Lifecycle Management (PLM) system.
 */
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    /**
     * Reserves a Document entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
    @GetMapping(value = "/Document/reserve")
    public void reserve(@RequestHeader("userId") String userId,
                        @RequestParam("reference") String reference,
                        @RequestParam("version") String version,
                        @RequestParam("iteration") int iteration) {
        documentService.reserve(userId, reference, version, iteration);
    }

    /**
     * Updates a Document entity.
     *
     * @param userId             The user ID making the request.
     * @param reference          The reference of the document.
     * @param version            The version of the document.
     * @param iteration          The iteration of the document.
     * @param documentAttribute1 The first attribute to update.
     * @param documentAttribute2 The second attribute to update.
     */
    @GetMapping(value = "/Document/update")
    public void update(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration,
                       @RequestParam("documentAttribute1") String documentAttribute1,
                       @RequestParam("documentAttribute2") String documentAttribute2) {
        documentService.update(userId, reference, version, iteration,
                documentAttribute1, documentAttribute2);
    }

    /**
     * Frees a reserved Document entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
    @GetMapping(value = "/Document/free")
    public void free(@RequestHeader("userId") String userId,
                     @RequestParam("reference") String reference,
                     @RequestParam("version") String version,
                     @RequestParam("iteration") int iteration) {
        documentService.free(userId, reference, version, iteration);
    }

    /**
     * Sets the state of a Document entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     * @param state     The new state of the document.
     */
    @GetMapping(value = "/Document/free")
    public void setState(@RequestHeader("userId") String userId,
                         @RequestParam("reference") String reference,
                         @RequestParam("version") String version,
                         @RequestParam("iteration") int iteration,
                         @RequestParam("state") String state) {
        documentService.setState(userId, reference, version, iteration, state);
    }

    /**
     * Revises a Document entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the document.
     * @param version   The version of the document.
     * @param iteration The iteration of the document.
     */
    @GetMapping(value = "/Document/revise")
    public void revise(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration) {
        documentService.revise(userId, reference, version, iteration);
    }
}