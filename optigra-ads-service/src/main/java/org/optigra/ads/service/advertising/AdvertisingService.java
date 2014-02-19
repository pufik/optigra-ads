package org.optigra.ads.service.advertising;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.advertising.Advertising;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 *
 */
public interface AdvertisingService {

    /**
     * 
     * Method for getting advertisings.
     * 
     * @date Feb 18, 2014
     * @author ivanursul
     * @param start
     * @param offset
     * @return paged result
     */
    PagedResult<Advertising> getAdvertisings(int start, int offset);

    /**
     * 
     * Creates advertising.
     * 
     * @date Feb 18, 2014
     * @author ivanursul
     * @param eq
     */
    void createAdvertising(Advertising advertising);

}
