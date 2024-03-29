package org.optigra.ads.service.user;

import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;

/**
 * User details service.
 *
 * @date Feb 4, 2014
 * @author Iurii Parfeniuk
 *
 */
public interface UserService {

	/**
	 * Method provides user by his identifier.
	 *
	 * @date Feb 4, 2014
	 * @author Iurii Parfeniuk
	 * @param userId
	 *            - user's identifier
	 * @return user - {@link User}
	 */
	User getUserById(Long userId);

	/**
	 * Method provides user by his login and password.
	 *
	 * @date Feb 4, 2014
	 * @author Iurii Parfeniuk
	 * @param login
	 *            - user's login
	 * @param password
	 *            - user's password
	 * @return user - {@link User}
	 */
	User getUserByLoginAndPassword(String login, String password);

	/**
	 * Method provides user by his login.
	 * @date Jun 11, 2014
	 * @param login - user's login
	 * @return {@link User}
	 */
	User getUserByLogin(final String login);

	/**
     *
     * Method for creating user.
     *
     * @date Feb 13, 2014
     * @author ivanursul
     * @param user User to be saved
     */
    void createUser(User user);

    /**
     * @date Feb 24, 2014
     * @author ivanursul
     * @param offset
     * @param limit
     * @return
     */
    PagedResult<User> getUsers(int offset, int limit);

    /**
     * Updates user
     * @param user
     */
	void update(User user);

	/**
	 * Delete user.
	 * @param userId
	 */
	void deleteUser(Long userId);

}
