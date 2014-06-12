package org.optigra.ads.dao.user;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.pagination.PagedSearch;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;



@RunWith(MockitoJUnitRunner.class)
public class DefaultUserDaoTest {

    @Captor
    private ArgumentCaptor<User> userCaptor;

    @Mock
    private PersistenceManager<User, Long> persistenceManager;

    @InjectMocks
    private final DefaultUserDao unit = new DefaultUserDao();

    @Test
    public void testFindById() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);

        // When
        when(persistenceManager.findById(User.class, userId)).thenReturn(expected);
        User actual = unit.findById(userId);

        // Then
        verify(persistenceManager).findById(User.class, userId);
        assertEquals(expected, actual);
    }

    @Test
    public void testPersist() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);

        // When
        unit.create(expected);

        // Then
        verify(persistenceManager).create(userCaptor.capture());
        assertEquals(expected, userCaptor.getValue());
    }

    @Test
    public void testRemove() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);

        // When
        unit.remove(expected);

        // Then
        verify(persistenceManager).remove(userCaptor.capture());
        assertEquals(expected, userCaptor.getValue());
    }

    @Test
    public void testUpdate() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);

        // When
        unit.update(expected);

        // Then
        verify(persistenceManager).update(userCaptor.capture());
        assertEquals(expected, userCaptor.getValue());
    }

    @Test
    public void testGetUserById() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);

        // When
        when(persistenceManager.findById(User.class, userId)).thenReturn(expected);
        User actual = unit.getUserById(userId);

        // Then
        verify(persistenceManager).findById(User.class, userId);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserByLoginAndPassword() {
        // Given
        long userId = 1;
        String login = "login";
        String password = "password";
        User expected = new User();
        expected.setId(userId);
        expected.setLogin(login);
        expected.setPassword(password);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login", login);
        parameters.put("password", password);
        Query<User> query = new Query<User>(User.class, Queries.FIND_USER_BY_LOGIN_AND_PASS.getQuery(), parameters);

        when(persistenceManager.executeQuerySingleResult(query)).thenReturn(expected);

        // When
        User actual = unit.getUserByLoginAndPassword(login, password);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void testGetUserByLogin() {
        // Given
        long userId = 1;
        String login = "login";
        User expected = new User();
        expected.setId(userId);
        expected.setLogin(login);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login", login);
        Query<User> query = new Query<User>(User.class, Queries.FIND_USER_BY_LOGIN.getQuery(), parameters);

        when(persistenceManager.executeQuerySingleResult(query)).thenReturn(expected);

        // When
        User actual = unit.getUserByLogin(login);

        // Then
        verify(persistenceManager).executeQuerySingleResult(query);
        assertEquals(expected, actual);
    }

    @Test
    public void testCreateUser() {
        // Given
        Long id = 1L;
        User user = new User();
        user.setId(id);

        // When
        unit.createUser(user);

        // Then
        verify(persistenceManager).create(userCaptor.capture());

        assertEquals(user, userCaptor.getValue());
    }

    @Test
    public void testGetUsers() {
        // Given
        int offset = 20;
        int limit = 0;
        long count = 100;
        User user1 = new User();
        user1.setRole(UserRole.ADMIN);
        List<User> entities = Arrays.asList(user1);

        Queries query = Queries.FIND_USERS;
        Map<String, Object> parameters = Collections.emptyMap();
        Query<User> jpQuery = new Query<User>(User.class, query.getQuery(), parameters );
        PagedSearch<User> search = new PagedSearch<>(offset, limit, jpQuery);
        PagedResult<User> expected = new PagedResult<User>(offset, limit, count, entities);

        // When
        when(persistenceManager.search(search)).thenReturn(expected);
        PagedResult<User> actual = unit.getUsers(offset, limit);

        // Then
        verify(persistenceManager).search(search);

        assertEquals(expected, actual);
    }
}
