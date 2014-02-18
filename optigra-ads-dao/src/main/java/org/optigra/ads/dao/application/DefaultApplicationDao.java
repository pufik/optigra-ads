package org.optigra.ads.dao.application;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.application.Application;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;


/**
 * Default implementation of Application Dao layer.
 *
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
@Repository("applicationDao")
public class DefaultApplicationDao extends AbstractDao<Application, Long>
        implements ApplicationDao {

    @Override
    protected Class<Application> getEntityClass() {
        return Application.class;
    }

    @Override
    public void createApplication(final Application application) {
        Assert.isNull(application.getId());
        create(application);
    }

    @Override
    public PagedResult<Application> getApplications(final int offset, final int limit) {

        Queries query = Queries.FIND_APPLICATIONS;
        PagedSearch pagedSearch = new PagedSearch(offset, limit, query, Collections.<String,Object>emptyMap());

        return search(pagedSearch);
    }

    @Override
    public Application getApplicationById(final String applicationId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appId", applicationId);

        return executeSingleResultNamedQuery(Queries.FIND_APPLICATION_BY_ID.getQueryName(), parameters);
    }

    @Override
    public void deleteApplication(final Application application) {
        remove(application);
    }
}
