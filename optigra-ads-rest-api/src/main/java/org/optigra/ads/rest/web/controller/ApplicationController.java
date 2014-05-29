package org.optigra.ads.rest.web.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.facade.resource.application.ApplicationStatusResource;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.optigra.ads.model.application.ApplicationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Resource(name = "applicationFacade")
    private ApplicationFacade applicationFacade;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public PagedResultResource<ApplicationResource> getApplications(@RequestParam(value = "offset", defaultValue = "0") final int offset,
            @RequestParam(value = "limit", defaultValue = "20") final int limit) {
        return applicationFacade.getApplications(offset, limit);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.GET)
    @ResponseBody
    public ApplicationResource getApplication(@PathVariable("appId") final String applicationId) {
        return applicationFacade.getApplication(applicationId);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_STATUS, method = RequestMethod.GET)
    @ResponseBody
    public ApplicationStatusResource getApplicationStatus(@PathVariable("appId") final String applicationId) {
        return applicationFacade.getApplicationStatus(applicationId);
    }

    @RequestMapping(value = ResourceUri.CERTIFICATE, method = RequestMethod.GET)
    @ResponseBody
    public CertificateResource getCertificate(@PathVariable("appId") final String applicationId) {
        return applicationFacade.getCertificate(applicationId);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ApplicationResource createApplication(@RequestBody final ApplicationResource applicationResource) {
        logger.info("Creating application: {}", applicationResource);

        applicationResource.setStatus(ApplicationStatus.UNPAID);
        return applicationFacade.createApplication(applicationResource);
    }

    @RequestMapping(value = ResourceUri.APPLICATION_NOTIFICATION, method = RequestMethod.POST)
    @ResponseBody
    public MessageResource sendApnsMessage(@PathVariable("appId") final String applicationId, @RequestBody final NotificationResource resource) {
        logger.info("Sending application: {} to Application with Id: {}", resource, applicationId);

        applicationFacade.sendNotificationMessage(applicationId, resource);
        return new MessageResource(MessageType.INFO, "Messages are going to be sent");
    }

    @RequestMapping(value = ResourceUri.CERTIFICATE, method = RequestMethod.POST)
    @ResponseBody
    public MessageResource createApnsCertificate(@PathVariable("appId") final String applicationId, @Valid @RequestBody final CertificateResource resource) {
        logger.info("Creating certificate for applicationId: {} , {}", applicationId, resource);

        applicationFacade.createCertificate(applicationId, resource);
        return new MessageResource(MessageType.INFO, "Certificate created");
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateApplication(@PathVariable("appId") final String applicationId, @RequestBody final ApplicationResource applicationResource) {
        logger.info("Updating application, id: {}, resource: {}", applicationId, applicationResource);

        applicationFacade.updateApplication(applicationId, applicationResource);
        return new MessageResource(MessageType.INFO, "Application Updated");
    }

    @RequestMapping(value = ResourceUri.CERTIFICATE_BY_ID, method = RequestMethod.PUT)
    @ResponseBody
    public MessageResource updateCertificate(@PathVariable("appId") final String applicationId, @PathVariable("certificateId") final Long certificateId,
            @Valid @RequestBody final CertificateResource resource) {
        logger.info("Updating certificate for {} , id: {}, resource: {}", applicationId, certificateId, resource);

        applicationFacade.updateCertificate(applicationId, certificateId, resource);
        return new MessageResource(MessageType.INFO, "Certificate updated");
    }

    @RequestMapping(value = ResourceUri.APPLICATION_BY_ID, method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteApplication(@PathVariable("appId") final String applicationId) {
        logger.info("Deleting application: {}", applicationId);

        applicationFacade.deleteApplication(applicationId);
        return new MessageResource(MessageType.INFO, "Application deleted");
    }

    @RequestMapping(value = ResourceUri.CERTIFICATE, method = RequestMethod.DELETE)
    @ResponseBody
    public MessageResource deleteCertificate(@PathVariable("appId") final String applicationId) {
        logger.info("Deleting certificate for {}", applicationId);

        applicationFacade.deleteCertificate(applicationId);
        return new MessageResource(MessageType.INFO, "Certificate deleted");
    }
}
