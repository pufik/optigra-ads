package org.optigra.ads.service.user;

import java.math.BigDecimal;

import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
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

	@Override
	public User getUserById(final Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByLoginAndPassword(final String login, final String password) {
		User user = new User();
		user.setId(BigDecimal.ONE.longValue());
		user.setRole(UserRole.ADMIN);

		return user;
	}
}
