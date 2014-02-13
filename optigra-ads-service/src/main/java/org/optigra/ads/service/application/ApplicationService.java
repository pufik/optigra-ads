package org.optigra.ads.service.application;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.application.Application;

/**
 * Service interface for facade layer.
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
public interface ApplicationService {
    
    /**
     * Method for storing application.
     * @date Feb 12, 2014
     * @author ivanursul
     * @param application Entity to be stored.
     */
    void createApplication(Application application);

    /**
     * Getting applications.
     * @date Feb 12, 2014
     * @author ivanursul
     * @param start
     * @param offset
     * @return
     */
    PagedResult<Application> getApplications(int start, int offset);

    /** Method for getting application status.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId Id of application, whose status we need.
     * @return Application status.
     */
    String getApplicationStatus(String applicationId);
}
