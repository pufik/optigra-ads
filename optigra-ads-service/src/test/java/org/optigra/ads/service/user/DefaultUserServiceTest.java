package org.optigra.ads.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.user.UserDao;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {
	
    @Mock
    private UserDao defaultUserDao;
    
    @InjectMocks
	private DefaultUserService unit = new DefaultUserService();

	@Test
	public void testGetUserById() {
		// Given
	    long userId = BigDecimal.ONE.longValue();
		User expectedUser = new User();
		expectedUser.setId(userId);
		
		// When
        when(defaultUserDao.getUserById(anyLong())).thenReturn(expectedUser);
		User actualUser = unit.getUserById(userId);

		// Then
		verify(defaultUserDao).getUserById(userId);
		assertEquals(expectedUser, actualUser);
	}
	
	@Test
	public void testGetUserByLoginAndPassword() {
		// Given
		String login = "login";
		String password = "password";
		User expectedUser = new User();
		expectedUser.setId(BigDecimal.ONE.longValue());
		expectedUser.setRole(UserRole.ADMIN);

		// When
		when(defaultUserDao.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(expectedUser);
		User actualUser = unit.getUserByLoginAndPassword(login, password);

		// Then
		verify(defaultUserDao).getUserByLoginAndPassword(login, password);
		assertEquals(expectedUser, actualUser);
	}

}
