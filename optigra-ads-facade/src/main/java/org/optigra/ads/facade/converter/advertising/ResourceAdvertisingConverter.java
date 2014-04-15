package org.optigra.ads.facade.converter.advertising;

import java.util.Date;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.model.advertising.Advertising;
import org.springframework.stereotype.Component;

/**
 * @date Feb 19, 2014
 * @author ivanursul
 *
 */
@Component("resourceAdvertisingConverter")
public class ResourceAdvertisingConverter extends AbstractConverter<AdvertisingResource, Advertising> {

    @Override
    public Advertising convert(final AdvertisingResource source, final Advertising target) {
        
        target.setDescription(source.getDescription());
        target.setDestinationUrl(source.getDestinationUrl());
        target.setImageUrl(source.getImageUrl());
        target.setLogoUrl(source.getLogoUrl());
        target.setTitle(source.getTitle());
        target.setUpdateDate(new Date());
        
        return target;
    }

    @Override
    public Advertising convert(final AdvertisingResource source) {
        return convert(source, new Advertising());
    }

}
