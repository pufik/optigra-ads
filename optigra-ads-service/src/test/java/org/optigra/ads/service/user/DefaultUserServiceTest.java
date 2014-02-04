package org.optigra.ads.service.user;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {
	
	private DefaultUserService unit = new DefaultUserService();

	@Test
	public void testGetUserById() {
		// Given
		User expextedUser = null; 

		// When
		User actualUser = unit.getUserById(BigDecimal.ONE.longValue());

		// Then
		assertEquals(expextedUser, actualUser);
	}
	
	@Test
	public void testGetUserByLoginAndPassword() {
		// Given
		String login = "login";
		String password = "password";
		User expextedUser = new User();
		expextedUser.setId(BigDecimal.ONE.longValue());
		expextedUser.setRole(UserRole.ADMIN);

		// When
		User actualUser = unit.getUserByLoginAndPassword(login, password);

		// Then
		assertEquals(expextedUser, actualUser);
	}

}
