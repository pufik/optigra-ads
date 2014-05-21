package org.optigra.ads.facade.application;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.apns.model.device.ApnsNotifiableDevice;
import org.optigra.ads.apns.model.notification.Notification;
import org.optigra.ads.content.service.ContentService;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.facade.resource.certificate.CertificateResource;
import org.optigra.ads.facade.resource.notification.NotificationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.certificate.Certificate;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.notification.executor.NotificationBatchExecutor;
import org.optigra.ads.notification.lookup.DeviceNotificationLookupService;
import org.optigra.ads.notification.service.DeviceNotificationService;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.application.ApplicationService;
import org.optigra.ads.service.certificate.CertificateService;
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
    
    @Resource(name = "certificateResourceConverter")
    private Converter<CertificateResource, Certificate> certificateResourceConverter;

    @Resource(name = "certificateConverter")
    private Converter<Certificate, CertificateResource> certificateConverter;
    
    @Resource(name = "certificateService")
    private CertificateService certificateService;
    
    @Resource(name = "applicationService")
    private ApplicationService applicationService;
    
    @Resource(name = "sessionService")
    private SessionService sessionService;
    
    
	@Resource(name = "apnsBatchExecutor")
	private NotificationBatchExecutor notificationBatchExecutor;
    
    // TODO: Place new staff in better position
	@Resource(name = "apnsNotificationLookupService")
	private DeviceNotificationLookupService<ApnsNotifiableDevice> notificationLookupService;
	
	@Resource(name = "contentService")
	private ContentService contentService;
    
    
    @Override
    public ApplicationResource createApplication(final ApplicationResource applicationResource) {
        Application application = applicationResourceConverter.convert(applicationResource);
        application.setOwner(sessionService.getCurrentSession().getUser());
        application.setCreateDate(new Date());

        applicationService.createApplication(application);
        return applicationConverter.convert(application);
    }

    @Override
    public PagedResultResource<ApplicationResource> getApplications(final int offset, final int limit) {
        PagedResult<Application> applications = applicationService.getApplications(offset, limit);
        List<ApplicationResource> applicationResources = applicationConverter.convertAll(applications.getEntities());

        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setEntities(applicationResources);

        pagedSearchConverter.convert(applications, pagedResult);
        return pagedResult;
    }

    @Override
    public String getApplicationStatus(final String applicationId) {
        return applicationService.getApplicationStatus(applicationId);
    }

    @Override
    public ApplicationResource getApplication(final String applicationId) {
        Application application = applicationService.getApplication(applicationId);
        return applicationConverter.convert(application);
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
	public void sendNotificationMessage(String applicationId, NotificationResource notificationResource) {
		Notification notification = notificationResourceConverter.convert(notificationResource);
		Application application = applicationService.getApplication(applicationId);
		
		Certificate certificate = certificateService.getCertificateByApplication(applicationId);
		InputStream inputStream = contentService.getContentByPath(certificate.getPath());
		DeviceNotificationService<ApnsNotifiableDevice> apnsNotificationService = notificationLookupService.lookup(inputStream, certificate);
		
		notificationBatchExecutor.process(application,  notification, apnsNotificationService);
	}

	@Override
	public void createCertificate(String applicationId, CertificateResource resource) {
		Certificate certificate = certificateResourceConverter.convert(resource);
		certificate.setOwner(sessionService.getCurrentSession().getUser());   
		certificate.setCreateDate(new Date());                      
		
		certificateService.createCertificate(applicationId, certificate);
	}

	@Override
	public void updateCertificate(String applicationId, Long certificateId, CertificateResource resource) {
		Certificate certificate = certificateService.getCertificate(certificateId);
		certificateResourceConverter.convert(resource, certificate);
		certificateService.updateCertificate(certificate);
	}

	@Override
	public CertificateResource getCertificate(String applicationId) {
		Certificate certificate = certificateService.getCertificateByApplication(applicationId);
		return certificateConverter.convert(certificate);
	}

	@Override
	public void deleteCertificate(String applicationId) {
		certificateService.deleteCertificate(applicationId);
	}
}
