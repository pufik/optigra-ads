package org.optigra.ads.facade.advertising;

import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.advertising.Advertising;
import org.optigra.ads.service.advertising.AdvertisingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 */
@Component("advertisingFacade")
@Transactional
public class DefaultAdvertisingFacade implements AdvertisingFacade {

    @Resource(name = "advertisingService")
    private AdvertisingService advertisingService;

    @Resource(name = "advertisingConverter")
    private Converter<Advertising, AdvertisingResource> advertisingConverter;

    @Resource(name = "resourceAdvertisingConverter")
    private Converter<AdvertisingResource, Advertising> resourceAdvertisingConverter;
    
    @Resource(name = "pagedSearchConverter")
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;

    @Override
    public PagedResultResource<AdvertisingResource> getAdvertisings(final int offset, final int limit) {

        PagedResult<Advertising> advertisings = advertisingService.getAdvertisings(offset, limit);
        List<AdvertisingResource> advertisingResources = advertisingConverter.convertAll(advertisings.getEntities());

        PagedResultResource<AdvertisingResource> pagedResultResource = new PagedResultResource<>(ResourceUri.ADVERTISING);
        pagedSearchConverter.convert(advertisings, pagedResultResource);
        pagedResultResource.setEntities(advertisingResources);

        return pagedResultResource;
    }

    @Override
    public void createAdvertising(final AdvertisingResource advertisingResource) {
        Advertising advertising = resourceAdvertisingConverter.convert(advertisingResource);
        advertisingService.createAdvertising(advertising);
    }
}
