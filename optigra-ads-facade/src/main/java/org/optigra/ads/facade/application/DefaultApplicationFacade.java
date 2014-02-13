package org.optigra.ads.facade.application;

import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.service.application.ApplicationService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 * 
 */
@Component("applicationFacade")
@Transactional
public class DefaultApplicationFacade implements ApplicationFacade {

    @Resource(name = "applicationDTOConverter")
    private Converter<ApplicationResource, Application> applicationDTOConverter;
    
    @Resource(name = "pagedSearchConverter")
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;

    @Resource(name = "applicationConverter")
    private Converter<Application, ApplicationResource> applicationConverter;

    @Resource(name = "applicationService")
    private ApplicationService applicationService;

    @Override
    public void createApplication(final ApplicationResource applicationResource) {
        
        // Convert from dto to entity
        Application application = applicationDTOConverter.convert(applicationResource);

        // Store application entity
        applicationService.createApplication(application);
    }

    @Override
    public PagedResultResource<ApplicationResource> getApplications(final int start, final int offset) {
        
        // Get List of Applications from service layer
        PagedResult<Application> applications = applicationService.getApplications(start, offset);
        
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
}
