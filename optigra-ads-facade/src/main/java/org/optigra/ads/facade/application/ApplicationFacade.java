package org.optigra.ads.facade.application;

import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.application.ApplicationResource;

/**
 * Facade layer for Application Domain Set.
 *
 * @date Feb 11, 2014
 * @author ivanursul
 */
public interface ApplicationFacade {

    /**
     * Method for storing application.
     *
     * @date Feb 12, 2014
     * @author ivanursul
     * @param applicationResource
     *            dto
     */
    void createApplication(ApplicationResource applicationResource);

    /**
     * Method for getting list of applications.
     *
     * @date Feb 12, 2014
     * @author ivanursul
     * @param offset
     *            start position.
     * @param limit
     *            size of list
     * @return list of applications.
     */
    PagedResultResource<ApplicationResource> getApplications(int offset, int limit);

    /**
     * Method, that will send status of application.
     *
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId
     *            App id
     * @return String status.
     */
    String getApplicationStatus(String applicationId);

    /**
     * @date Feb 14, 2014
     * @author ivanursul
     * @param applicationId
     * @return
     */
    ApplicationResource getApplication(String applicationId);

    /**
     * Method for deleting application.
     *
     * @date Feb 17, 2014
     * @author ivanursul
     * @param applicationId
     *            application unique identifier.
     */
    void deleteApplication(String applicationId);
}
