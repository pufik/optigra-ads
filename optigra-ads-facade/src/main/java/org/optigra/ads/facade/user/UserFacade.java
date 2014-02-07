package org.optigra.ads.facade.user;

import org.optigra.ads.facade.dto.UserResource;

/**
 * 
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public interface UserFacade {

    UserResource getUserById(Long id);
    
}
