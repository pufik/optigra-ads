package org.optigra.ads.facade.converter.application;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.application.ApplicationResource;
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
        return convert(application, new ApplicationResource());
    }

    @Override
    public ApplicationResource convert(final Application application, final ApplicationResource applicationResource) {

        applicationResource.setApplicationId(application.getApplicationId());
        applicationResource.setId(application.getId());
        applicationResource.setName(application.getName());
        applicationResource.setStatus(application.getStatus());
        applicationResource.setUrl(application.getUrl());
        applicationResource.setGroupId(application.getGroupId());
        applicationResource.setGroupName(application.getGroupName());
        applicationResource.setImageUrl(application.getImageUrl());

        return applicationResource;
    }
}
