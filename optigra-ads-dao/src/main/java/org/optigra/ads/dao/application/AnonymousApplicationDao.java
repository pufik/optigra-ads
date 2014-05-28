package org.optigra.ads.dao.application;

import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.query.Queries;
import org.springframework.stereotype.Repository;

/**
 * Application DAo for device not secured query.
 *
 * @date May 28, 2014
 * @author Iurii Parfeniuk
 */
@Repository("anonymousApplicationDao")
public class AnonymousApplicationDao extends DefaultApplicationDao {

    @Override
    public Application getApplicationById(final String applicationId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("appId", applicationId);

        return executeSingleResultQuery(Queries.FIND_APPLICATION_BY_ID_FOR_DEVICE.getQuery(), parameters);
    }
}
