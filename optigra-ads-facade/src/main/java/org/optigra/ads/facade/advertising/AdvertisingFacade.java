package org.optigra.ads.facade.advertising;

import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 *
 */
public interface AdvertisingFacade {

    /**
     * 
     * Method for getting advertisings.
     * 
     * @date Feb 18, 2014
     * @author ivanursul
     * @param start start position
     * @param offset offset
     * @return
     */
    PagedResultResource<AdvertisingResource> getAdvertisings(int start, int offset);

}
