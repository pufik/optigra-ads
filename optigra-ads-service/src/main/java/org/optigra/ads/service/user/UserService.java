package org.optigra.ads.service.user;

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

}
