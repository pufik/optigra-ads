package org.optigra.ads.service.application;

import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;
import org.optigra.ads.model.pagination.PagedResult;

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
     * @param offset
     * @param limit
     * @return
     */
    PagedResult<Application> getApplications(int offset, int limit);

    /** Method for getting application status.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId Id of application, whose status we need.
     * @return Application status.
     */
    ApplicationStatus getApplicationStatus(String applicationId);

    /**
     *
     * Gets application by App Id
     *
     * @date Feb 14, 2014
     * @author ivanursul
     * @param applicationId
     * @return
     */
    Application getApplication(String applicationId);

    /**
     *
     * Delete application by id.
     *
     * @date Feb 17, 2014
     * @author ivanursul
     * @param applicationId
     */
    void deleteApplication(String applicationId);

    /**
     * @date Mar 19, 2014
     * @author ivanursul
     * @param application
     */
    void updateApplication(Application application);

}
