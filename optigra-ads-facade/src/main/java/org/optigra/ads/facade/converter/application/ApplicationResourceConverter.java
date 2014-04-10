package org.optigra.ads.facade.converter.application;

import java.util.Date;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.springframework.stereotype.Component;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
@Component("applicationResourceConverter")
public class ApplicationResourceConverter extends AbstractConverter<ApplicationResource, Application> {

    @Override
    public Application convert(final ApplicationResource applicationResource) {

        return convert(applicationResource, new Application());
    }

    @Override
    public Application convert(final ApplicationResource applicationResource, final Application application) {

        application.setName(applicationResource.getName());
        application.setStatus(applicationResource.getStatus());
        application.setUrl(applicationResource.getUrl());
        application.setGroupId(applicationResource.getGroupId());
        application.setGroupName(applicationResource.getGroupName());
        application.setImageUrl(applicationResource.getImageUrl());
        application.setUpdateDate(new Date());

        return application;
    }

}
