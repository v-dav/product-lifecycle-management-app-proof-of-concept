package plm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import plm.services.PartService;

/**
 * Controller for managing Part entities in the Product Lifecycle Management (PLM) system.
 */
public class PartController {

    @Autowired
    private PartService partService;

    /**
     * Reserves a Part entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the part.
     * @param version   The version of the part.
     * @param iteration The iteration of the part.
     */
    @GetMapping(value = "/Part/reserve")
    public void reserve(@RequestHeader("userId") String userId,
                        @RequestParam("reference") String reference,
                        @RequestParam("version") String version,
                        @RequestParam("iteration") int iteration) {
        partService.reserve(userId, reference, version, iteration);
    }

    /**
     * Updates a Part entity.
     *
     * @param userId         The user ID making the request.
     * @param reference      The reference of the part.
     * @param version        The version of the part.
     * @param iteration      The iteration of the part.
     * @param partAttribute1 The first attribute to update.
     * @param partAttribute2 The second attribute to update.
     */
    @GetMapping(value = "/Part/update")
    public void update(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration,
                       @RequestParam("partAttribute1") String partAttribute1,
                       @RequestParam("partAttribute2") String partAttribute2) {
        partService.update(userId, reference, version, iteration,
                partAttribute1, partAttribute2);
    }

    /**
     * Frees a reserved Part entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the part.
     * @param version   The version of the part.
     * @param iteration The iteration of the part.
     */
    @GetMapping(value = "/Part/free")
    public void free(@RequestHeader("userId") String userId,
                     @RequestParam("reference") String reference,
                     @RequestParam("version") String version,
                     @RequestParam("iteration") int iteration) {
        partService.free(userId, reference, version, iteration);
    }

    /**
     * Sets the state of a Part entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the part.
     * @param version   The version of the part.
     * @param iteration The iteration of the part.
     * @param state     The new state of the part.
     */
    @GetMapping(value = "/Part/free")
    public void setState(@RequestHeader("userId") String userId,
                         @RequestParam("reference") String reference,
                         @RequestParam("version") String version,
                         @RequestParam("iteration") int iteration,
                         @RequestParam("state") String state) {
        partService.setState(userId, reference, version, iteration, state);
    }

    /**
     * Revises a Part entity.
     *
     * @param userId    The user ID making the request.
     * @param reference The reference of the part.
     * @param version   The version of the part.
     * @param iteration The iteration of the part.
     */
    @GetMapping(value = "/Part/revise")
    public void revise(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration) {
        partService.revise(userId, reference, version, iteration);
    }
}