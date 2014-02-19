package org.optigra.ads.dao.advertising;

import java.util.Collections;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.advertising.Advertising;
import org.springframework.stereotype.Repository;

/**
 * @date Feb 18, 2014
 * @author ivanursul
 */
@Repository("advertisingDao")
public class DefaultAdvertisingDao extends AbstractDao<Advertising, Long> implements AdvertisingDao {

    @Override
    protected Class<Advertising> getEntityClass() {
        return Advertising.class;
    }

    @Override
    public PagedResult<Advertising> getAdvertisings(final int offset, final int limit) {

        Queries query = Queries.FIND_ADVERTISINGS;
        Map<String, Object> parameters = Collections.emptyMap();
        PagedSearch search = new PagedSearch(offset, limit, query, parameters);

        return search(search);
    }

}
