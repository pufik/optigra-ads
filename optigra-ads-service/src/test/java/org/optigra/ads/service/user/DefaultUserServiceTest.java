package org.optigra.ads.service.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.user.UserDao;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class DefaultUserServiceTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
	private final DefaultUserService unit = new DefaultUserService();

	@Test
	public void testGetUserById() {
		// Given
	    long userId = BigDecimal.ONE.longValue();
		User expectedUser = new User();
		expectedUser.setId(userId);

		// When
        when(userDao.getUserById(anyLong())).thenReturn(expectedUser);
		User actualUser = unit.getUserById(userId);

		// Then
		verify(userDao).getUserById(userId);
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
		when(userDao.getUserByLoginAndPassword(anyString(), anyString())).thenReturn(expectedUser);
		User actualUser = unit.getUserByLoginAndPassword(login, password);

		// Then
		verify(userDao).getUserByLoginAndPassword(login, password);
		assertEquals(expectedUser, actualUser);
	}

	@Test
    public void testGetUserByLogin() {
        // Given
        String login = "login";
        User expectedUser = new User();
        expectedUser.setId(BigDecimal.ONE.longValue());
        expectedUser.setRole(UserRole.ADMIN);

        // When
        when(userDao.getUserByLogin(anyString())).thenReturn(expectedUser);
        User actualUser = unit.getUserByLogin(login);

        // Then
        verify(userDao).getUserByLogin(login);
        assertEquals(expectedUser, actualUser);
    }

	@Test
	public void testGetUsers() {
	    // Given
	    int offset = 1;
	    int limit = 23;
	    long count = 100;
        User user1 = new User();
        List<User> entities = Arrays.asList(user1);
        PagedResult<User> expected = new PagedResult<User>(offset, limit, count, entities);

        // When
        when(userDao.getUsers(anyInt(), anyInt())).thenReturn(expected);

	    PagedResult<User> actual = unit.getUsers(offset, limit);

	    // Then
	    verify(userDao).getUsers(offset, limit);
	    assertEquals(expected, actual);
	}

	@Test
	public void testUpdateUser() throws Exception {
		// Given
		String email = "email";
		User user = new User();
		user.setEmail(email);

		// When
		unit.update(user);

		// Then
		verify(userDao).update(user);
	}

	@Test
	public void testDeleteUser() throws Exception {
		// Given
		Long userId = 1L;

		// When
		unit.deleteUser(userId);

		// Then
		verify(userDao).removeById(userId);
	}
}
