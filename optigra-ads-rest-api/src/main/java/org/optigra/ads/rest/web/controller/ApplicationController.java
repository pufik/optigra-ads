package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Application presentation layer.
 *
 * @date Feb 11, 2014
 * @author ivanursul
 */
@Controller
@RequestMapping(value = ResourceUri.APPLICATION)
public class ApplicationController extends BaseController {

    @Resource(name = "applicationFacade")
    private ApplicationFacade facade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResultResource<ApplicationResource> getApplications(@RequestParam(value = "offset", defaultValue = "0") final int offset,
            @RequestParam(value = "limit", defaultValue = "20") final int limit) {

        return facade.getApplications(offset, limit);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public ApplicationResource getApplication(@PathVariable("appId") final String applicationId) {

        return facade.getApplication(applicationId);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_STATUS, method = RequestMethod.GET)
    @ResponseBody
    public MessageResource getApplicationStatus(@PathVariable("appId") final String applicationId) {

        String status = facade.getApplicationStatus(applicationId);

        return new MessageResource(MessageType.INFO, status);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApplicationResource createApplication(@RequestBody final ApplicationResource applicationResource) {
        return facade.createApplication(applicationResource);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateApplication(@PathVariable("appId") final String applicationId, @RequestBody final ApplicationResource applicationResource) {

        facade.updateApplication(applicationId, applicationResource);

        return new MessageResource(MessageType.INFO, "Application Updated");
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteApplication(@PathVariable("appId") final String applicationId) {

        facade.deleteApplication(applicationId);

        return new MessageResource(MessageType.INFO, "Application deleted");
    }
}
