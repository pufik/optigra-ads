package org.optigra.ads.dao.application;

import org.optigra.ads.dao.Dao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.pagination.PagedResult;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 */
public interface ApplicationDao extends Dao<Application, Long> {

    /**
     * @date Feb 12, 2014
     * @author ivanursul
     * @param offset
     * @param limit
     * @return
     */
    PagedResult<Application> getApplications(int offset, int limit);

    /**
     * Getting application.
     *
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId
     * @return application
     */
    Application getApplicationById(String applicationId);
    
}
