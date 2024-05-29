package plm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import plm.services.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Abstract class providing common functionality for managing entities
 * in the Product Lifecycle Management (PLM) system.
 */
public abstract class AbstractController implements Controller {

    @Autowired
    protected Service service;

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/reserve")
    public void reserve(@RequestHeader("userId") String userId,
                        @RequestParam("reference") String reference,
                        @RequestParam("version") String version,
                        @RequestParam("iteration") int iteration) {
        service.reserve(userId, reference, version, iteration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/update")
    public void update(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration,
                       @RequestParam("attribute1") String attribute1,
                       @RequestParam("attribute2") String attribute2) {
        service.update(userId, reference, version, iteration, attribute1, attribute2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/free")
    public void free(@RequestHeader("userId") String userId,
                     @RequestParam("reference") String reference,
                     @RequestParam("version") String version,
                     @RequestParam("iteration") int iteration) {
        service.free(userId, reference, version, iteration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/free")
    public void setState(@RequestHeader("userId") String userId,
                         @RequestParam("reference") String reference,
                         @RequestParam("version") String version,
                         @RequestParam("iteration") int iteration,
                         @RequestParam("state") String state) {
        service.setState(userId, reference, version, iteration, state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @GetMapping(value = "/revise")
    public void revise(@RequestHeader("userId") String userId,
                       @RequestParam("reference") String reference,
                       @RequestParam("version") String version,
                       @RequestParam("iteration") int iteration) {
        service.revise(userId, reference, version, iteration);
    }
}