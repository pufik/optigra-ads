package org.optigra.ads.dao.advertising;

import java.util.Collections;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.dao.Query;
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
        Query<Advertising> query = new Query<Advertising>(getEntityClass(), Queries.FIND_ADVERTISINGS.getQuery(), Collections.<String, Object> emptyMap());
        PagedSearch<Advertising> search = new PagedSearch<>(offset, limit, query);

        return search(search);
    }

}
