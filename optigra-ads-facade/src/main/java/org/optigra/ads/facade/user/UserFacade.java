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
     * @return user resource
     * @throws Exception 
     */
    UserResource getUser(Long id);

    /**
     * Gets current user from session
     * @return user resource
     */
    UserResource getCurrentUser();

    /**
     * Method for creating new user.
     * @date Feb 13, 2014
     * @author ivanursul
     * @param userResource
     */
    UserResource createUser(UserDetailsResource userResource);

    /**
     * @date Feb 24, 2014
     * @author ivanursul
     * @param offset
     * @param limit
     * @return
     */
    PagedResultResource<UserResource> getUsers(int offset, int limit);

    /**
     * Updates user.
     * @param userId
     * @param userResource
     */
	void updateUser(Long userId, UserDetailsResource userResource);

	/**
	 * Delete user entity
	 * @param userId
	 */
	void deleteUser(Long userId);
    
}
