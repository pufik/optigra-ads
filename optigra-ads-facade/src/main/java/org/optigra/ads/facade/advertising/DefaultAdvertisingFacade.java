package org.optigra.ads.facade.advertising;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.advertising.Advertising;
import org.optigra.ads.security.session.SessionService;
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

    @Resource(name = "sessionService")
    private SessionService sessionService;
    
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
    public AdvertisingResource createAdvertising(final AdvertisingResource advertisingResource) {
        Advertising advertising = resourceAdvertisingConverter.convert(advertisingResource);
        advertising.setOwner(sessionService.getCurrentSession().getUser());
        advertising.setCreateDate(new Date());
        
        advertisingService.createAdvertising(advertising);
        AdvertisingResource resource = advertisingConverter.convert(advertising);
        return resource;
    }

	@Override
	public void updateAdvertising(Long advertisingId, AdvertisingResource resource) {
		Advertising advertising = advertisingService.getAdvertising(advertisingId);
		resourceAdvertisingConverter.convert(resource, advertising);
		advertisingService.updateAdvertising(advertising);
	}

	@Override
	public AdvertisingResource getAdvertising(Long advertisingId) {
		Advertising advertising = advertisingService.getAdvertising(advertisingId);
		AdvertisingResource advertisingResource = advertisingConverter.convert(advertising);
		return advertisingResource;
	}

	@Override
	public void deleteAdvertising(Long advertisingId) {
		advertisingService.deleteAdvertising(advertisingId);
	}
}
