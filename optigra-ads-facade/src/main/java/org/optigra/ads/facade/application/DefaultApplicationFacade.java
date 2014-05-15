package org.optigra.ads.facade.application;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.application.ApplicationService;
import org.optigra.ads.service.notification.NotificationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 */
@Component("applicationFacade")
@Transactional
public class DefaultApplicationFacade implements ApplicationFacade {

    @Resource(name = "applicationResourceConverter")
    private Converter<ApplicationResource, Application> applicationResourceConverter;

    @Resource(name = "pagedSearchConverter")
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;

    @Resource(name = "applicationConverter")
    private Converter<Application, ApplicationResource> applicationConverter;
    
    @Resource(name = "notificationResourceConverter")
    private Converter<NotificationResource, Notification> notificationResourceConverter;

    @Resource(name = "applicationService")
    private ApplicationService applicationService;
    
    @Resource(name = "notificationService")
    private NotificationService notificationService;

    @Resource(name = "sessionService")
    private SessionService sessionService;
    
    @Override
    public ApplicationResource createApplication(final ApplicationResource applicationResource) {

        // Convert from dto to entity
        Application application = applicationResourceConverter.convert(applicationResource);
        application.setOwner(sessionService.getCurrentSession().getUser());
        application.setCreateDate(new Date());

        // Store application entity
        applicationService.createApplication(application);

        return applicationConverter.convert(application);
    }

    @Override
    public PagedResultResource<ApplicationResource> getApplications(final int offset, final int limit) {

        // Get List of Applications from service layer
        PagedResult<Application> applications = applicationService.getApplications(offset, limit);

        List<ApplicationResource> applicationResources = applicationConverter.convertAll(applications.getEntities());

        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setEntities(applicationResources);

        pagedSearchConverter.convert(applications, pagedResult);

        return pagedResult;
    }

    @Override
    public String getApplicationStatus(final String applicationId) {

        String applicationStatus = applicationService.getApplicationStatus(applicationId);

        return applicationStatus;
    }

    @Override
    public ApplicationResource getApplication(final String applicationId) {

        Application application = applicationService.getApplication(applicationId);
        ApplicationResource resource = applicationConverter.convert(application);

        return resource;
    }

    @Override
    public void deleteApplication(final String applicationId) {
        applicationService.deleteApplication(applicationId);
    }

    @Override
    public void updateApplication(final String applicationId, final ApplicationResource applicationResource) {
        Application application = applicationService.getApplication(applicationId);

        applicationResourceConverter.convert(applicationResource, application);

        applicationService.updateApplication(application);
    }

	@Override
	public void sendApnsMessage(String applicationId, NotificationResource notificationResource) {
		Notification notification = notificationResourceConverter.convert(notificationResource);
		Application application = applicationService.getApplication(applicationId);
		
		notificationService.send(application,  notification);
	}
}
