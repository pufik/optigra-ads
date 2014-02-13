package org.optigra.ads.rest.web.controller;

import org.optigra.ads.facade.resource.ApiDetailsResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for system stuff.
 * @author Iurii Parfeniuk
 */

@Controller
public class SystemController {

    private static final ApiDetailsResource API_DETAILS = new ApiDetailsResource();

    /**
     * Method returns REST API details.
     * @date Jan 24, 2014
     * @author Iurii Parfeniuk
     * @return details about REST API {@link ApiDetailsResource}
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApiDetailsResource getApiDetails() {
        return API_DETAILS;
    }
}
