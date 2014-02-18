package org.optigra.ads.dao.advertising;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.advertising.Advertising;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 *
 */
public interface AdvertisingDao {

    /**
     * 
     * Method for getting advertisings
     * 
     * @date Feb 18, 2014
     * @author ivanursul
     * @param start
     * @param offset
     * @return
     */
    PagedResult<Advertising> getAdvertisings(int start, int offset);

}
