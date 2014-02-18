package org.optigra.ads.facade.converter.advertising;

import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.model.advertising.Advertising;
import org.springframework.stereotype.Component;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 *
 */
@Component("advertisingConverter")
public class AdvertisingConverter extends AbstractConverter<Advertising, AdvertisingResource> {

    @Override
    public AdvertisingResource convert(final Advertising source, final AdvertisingResource target) {
        
        target.setDescription(source.getDescription());
        target.setDestinationUrl(source.getDestinationUrl());
        target.setImageUrl(source.getImageUrl());
        target.setLogoUrl(source.getLogoUrl());
        target.setTitle(source.getTitle());
        target.setUid(source.getId());
        
        return target;
    }

    @Override
    public AdvertisingResource convert(final Advertising source) {
        return convert(source, new AdvertisingResource());
    }

}
