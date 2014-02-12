package org.optigra.ads.facade.converter.application;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.dto.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.springframework.stereotype.Component;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
@Component("applicationConverter")
public class ApplicationConverter extends AbstractConverter<Application, ApplicationResource> {

    @Override
    public ApplicationResource convert(final Application application) {
        
        ApplicationResource applicationResource = new ApplicationResource();
        
        applicationResource.setApplicationId(application.getApplicationId());
        applicationResource.setId(application.getId());
        applicationResource.setName(application.getName());
        applicationResource.setStatus(application.getStatus());
        applicationResource.setUrl(application.getUrl());
        
        return applicationResource;
    }

}
