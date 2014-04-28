package org.optigra.ads.dao.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Default implementation of Application Dao layer.
 *
 * @date Feb 12, 2014
 * @author ivanursul
 */
@Repository("applicationDao")
public class DefaultApplicationDao extends AbstractDao<Application, Long> implements ApplicationDao {

    @Override
    protected Class<Application> getEntityClass() {
        return Application.class;
    }

    @Override
    public PagedResult<Application> getApplications(final int offset, final int limit) {
        Query<Application> query = new Query<Application>(getEntityClass(), Queries.FIND_APPLICATIONS.getQuery(), Collections.<String, Object> emptyMap());
        PagedSearch<Application> search = new PagedSearch<>(offset, limit, query);

        return search(search);
    }

    @Override
    public Application getApplicationById(final String applicationId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appId", applicationId);

        return executeSingleResultQuery(Queries.FIND_APPLICATION_BY_ID.getQuery(), parameters);
    }
}
