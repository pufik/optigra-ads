package org.optigra.ads.dao.user;

import org.optigra.ads.model.user.User;




/**
 * Interface, that will declare API for User Dao.
 * @date Jan 24, 2014
 * @author ivanursul
 *
 */
public interface UserDao {
    /**
     * Method provides user by his identifier
     * @date Feb 7, 2014
     * @author ivanursul
     * @param userId user identifier
     * @return user - {@link User}}
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
