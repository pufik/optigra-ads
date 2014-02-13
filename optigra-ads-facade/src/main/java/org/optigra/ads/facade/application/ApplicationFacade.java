package org.optigra.ads.facade.application;

import org.optigra.ads.facade.dto.PagedResultResource;
import org.optigra.ads.facade.dto.application.ApplicationResource;

/**
 * Facade layer for Application Domain Set.
 * 
 * @date Feb 11, 2014
 * @author ivanursul
 * 
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
     * @param start
     *            start position.
     * @param offset
     *            size of list
     * @return list of applications.
     */
    PagedResultResource<ApplicationResource> getApplications(int start, int offset);

    /** Method, that will send status of application.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param applicationId App id 
     * @return String status.
     */
    String getApplicationStatus(String applicationId);
}
