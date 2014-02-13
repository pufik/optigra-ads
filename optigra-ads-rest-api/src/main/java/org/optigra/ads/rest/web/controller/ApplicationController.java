package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;

import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.dto.MessageResource;
import org.optigra.ads.facade.dto.MessageType;
import org.optigra.ads.facade.dto.PagedResultResource;
import org.optigra.ads.facade.dto.ResourceUri;
import org.optigra.ads.facade.dto.application.ApplicationResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Application presentation layer.
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@Controller
@RequestMapping(value = ResourceUri.APPLICATION)
public class ApplicationController extends BaseController {

    @Resource(name = "applicationFacade")
    private ApplicationFacade facade;
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public MessageResource createApplication(@RequestBody final ApplicationResource applicationResource) {
        
        facade.createApplication(applicationResource);
        
        return new MessageResource(MessageType.INFO, "Application created");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResultResource<ApplicationResource> getApplications(@RequestParam(value = "start", defaultValue = "0") final int start, @RequestParam(value = "offset", defaultValue = "20") final int offset) {
        return facade.getApplications(start, offset);
    }
    
    @RequestMapping(value = ResourceUri.APPLICATION_STATUS, method = RequestMethod.GET)
    @ResponseBody
    public MessageResource getApplicationStatus(@PathVariable("appId") final String applicationId) {

        String status = facade.getApplicationStatus(applicationId);
        
        return new MessageResource(MessageType.INFO, status);
    }
    
}
