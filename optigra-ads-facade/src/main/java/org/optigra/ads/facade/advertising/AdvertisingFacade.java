package org.optigra.ads.facade.advertising;

import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 */
public interface AdvertisingFacade {

    /**
     * Method for getting advertisings.
     *
     * @date Feb 18, 2014
     * @author ivanursul
     * @param offset
     *            - start position
     * @param limit
     *            - maximum size
     * @return {@link PagedResultResource}
     */
    PagedResultResource<AdvertisingResource> getAdvertisings(int offset, int limit);

    /**
     * 
     * Creates advertising.
     * 
     * @date Feb 18, 2014
     * @author ivanursul
     * @param advertisingResource resource to be created.
     */
    void createAdvertising(AdvertisingResource advertisingResource);

}
