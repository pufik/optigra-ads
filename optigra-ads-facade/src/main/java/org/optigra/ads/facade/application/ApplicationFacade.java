package org.optigra.ads.facade.application;

import org.optigra.ads.facade.dto.application.ApplicationResource;


/**
 * Facade layer for Application Domain Set.
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
public interface ApplicationFacade {

    /**
     * Method for storing application.
     * @date Feb 12, 2014
     * @author ivanursul
     * @param applicationResource dto
     */
    void createApplication(ApplicationResource applicationResource);
}
