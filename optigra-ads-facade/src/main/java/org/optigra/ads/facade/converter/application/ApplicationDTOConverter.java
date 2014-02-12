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
@Component("applicationDTOConverter")
public class ApplicationDTOConverter extends AbstractConverter<ApplicationResource, Application> {

    @Override
    public Application convert(final ApplicationResource applicationResource) {

        return convert(applicationResource, new Application());
    }

    @Override
    public Application convert(final ApplicationResource applicationResource, final Application application) {

        application.setApplicationId(applicationResource.getApplicationId());
        application.setId(applicationResource.getId());
        application.setName(applicationResource.getName());
        application.setStatus(applicationResource.getStatus());
        application.setUrl(applicationResource.getUrl());

        return application;
    }

}
