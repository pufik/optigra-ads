package org.optigra.ads.service.advertising;

import javax.annotation.Resource;

import org.optigra.ads.dao.advertising.AdvertisingDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.advertising.Advertising;
import org.springframework.stereotype.Service;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 *
 */
@Service("advertisingService")
public class DefaultAdvertisingService implements AdvertisingService {

    @Resource(name = "advertisingDao")
    private AdvertisingDao advertisingDao;
    
    @Override
    public PagedResult<Advertising> getAdvertisings(final int start, final int offset) {
        return advertisingDao.getAdvertisings(start, offset);
    }

}
