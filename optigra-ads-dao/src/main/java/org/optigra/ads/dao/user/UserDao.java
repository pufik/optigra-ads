package org.optigra.ads.dao.user;

import java.util.List;

import org.optigra.ads.model.user.User;


/**
 * Interface, that will declare API for User Dao.
 * @date Jan 24, 2014
 * @author ivanursul
 *
 */
public interface UserDao {
    List<User> getUsers(int start, int length);
}
