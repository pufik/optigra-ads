package org.optigra.ads.facade.application;

import javax.annotation.Resource;

import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.dto.application.ApplicationResource;
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
public class DefaultApplicationFacade implements ApplicationFacade {

    @Resource(name = "applicationDTOConverter")
    private Converter<ApplicationResource, Application> applicationDTOConverter;
    
    @Resource(name = "applicationService")
    private ApplicationService applicationService;
    
    @Override
    @Transactional
    public void createApplication(final ApplicationResource applicationResource) {
        // Convert from dto to entity
        Application application = applicationDTOConverter.convert(applicationResource);
        
        // Store application entity
        applicationService.createApplication(application);
    }

}
