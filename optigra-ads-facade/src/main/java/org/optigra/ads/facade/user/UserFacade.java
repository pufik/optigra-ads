package org.optigra.ads.facade.user;

import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;

/**
 * 
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public interface UserFacade {

    /**
     * Get's user by id;
     * @date Feb 13, 2014
     * @author ivanursul
     * @param id
     * @return
     */
    UserResource getUserById(Long id);

    /**
     * Method for creating new user.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param userResource
     */
    void createUser(UserDetailsResource userResource);

    /**
     * @date Feb 24, 2014
     * @author ivanursul
     * @param offset
     * @param limit
     * @return
     */
    PagedResultResource<UserResource> getUsers(int offset, int limit);
    
}
