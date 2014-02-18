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
     * @param offset
     * @param limit
     * @return
     */
    PagedResult<Application> getApplications(int offset, int limit);

    /**
     *
     * Getting application.
     *
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId
     * @return application
     */
    Application getApplicationById(String applicationId);

    /**
     *
     * Deleting application.
     *
     * @date Feb 17, 2014
     * @author ivanursul
     * @param applicationId
     */
    void deleteApplication(Application application);

}
