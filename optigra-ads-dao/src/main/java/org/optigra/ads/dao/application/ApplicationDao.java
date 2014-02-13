package org.optigra.ads.dao.application;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.application.Application;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 * 
 */
public interface ApplicationDao {

    /**
     * Create application method in Dao layer.
     * 
     * @date Feb 12, 2014
     * @author ivanursul
     * @param application
     */
    void createApplication(Application application);

    /**
     * @date Feb 12, 2014
     * @author ivanursul
     * @param start
     * @param offset
     * @return
     */
    PagedResult<Application> getApplications(int start, int offset);

    /**
     * Getting application.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId
     * @return application
     */
    Application getApplicationById(String applicationId);

}
