package plm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plm.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Controller for managing Part entities
 * in the Product Lifecycle Management (PLM) system.
 */
@RestController
@RequestMapping("/Part")
public class PartController extends AbstractController {

    /**
     * Sets the PartService for this controller.
     *
     * @param partService The PartService to set.
     */
    @Autowired
    public void setService(PartService partService) {
        this.service = partService;
    }
}