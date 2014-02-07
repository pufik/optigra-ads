package org.optigra.ads.service.user;

import javax.annotation.Resource;

import org.optigra.ads.dao.user.UserDao;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Service;

/**
 * Default user service.
 * 
 * @date Feb 4, 2014
 * @author Iurii Parfeniuk
 * 
 */

@Service("userService")
public class DefaultUserService implements UserService {

    @Resource
    private UserDao defaultUserDao;
    
	@Override
	public User getUserById(final Long userId) {
		return defaultUserDao.getUserById(userId);
	}

	@Override
	public User getUserByLoginAndPassword(final String login, final String password) {
		return defaultUserDao.getUserByLoginAndPassword(login, password);
	}
}
