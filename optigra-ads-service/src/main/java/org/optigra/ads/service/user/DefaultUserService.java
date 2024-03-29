package org.optigra.ads.service.user;

import javax.annotation.Resource;

import org.optigra.ads.dao.user.UserDao;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Default user service.
 *
 * @date Feb 4, 2014
 * @author Iurii Parfeniuk
 *
 */

@Service("userService")
public class DefaultUserService implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

	@Override
	public User getUserById(final Long userId) {
		User user = userDao.getUserById(userId);
		Assert.notNull(user);
		return user;
	}

	@Override
	public User getUserByLoginAndPassword(final String login, final String password) {
		return userDao.getUserByLoginAndPassword(login, password);
	}

	@Override
    public User getUserByLogin(final String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void createUser(final User user) {
        userDao.createUser(user);
    }

    @Override
    public PagedResult<User> getUsers(final int offset, final int limit) {
        return userDao.getUsers(offset, limit);
    }

	@Override
	public void update(final User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(final Long userId) {
		userDao.removeById(userId);
	}
}
