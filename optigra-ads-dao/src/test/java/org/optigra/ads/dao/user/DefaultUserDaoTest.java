package org.optigra.ads.dao.user;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.user.User;



@RunWith(MockitoJUnitRunner.class)
public class DefaultUserDaoTest {

    private static final String TABLE_TOKEN = "$table";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM " + TABLE_TOKEN + " a WHERE a IN(%s) ";
    
    @Captor
    private ArgumentCaptor<User> userCaptor;
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery<User> typedQuery;

    @Mock
    private TypedQuery<Long> longTypedQuery;
    
    @InjectMocks
    private DefaultUserDao unit = new DefaultUserDao();

    @Test
    public void testFindById() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);
        
        // When
        when(entityManager.find(Matchers.<Class<User>>any(), anyLong())).thenReturn(expected);
        User actual = unit.findById(userId);
        
        // Then
        verify(entityManager).find(User.class, userId);
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

        verify(entityManager).persist(userCaptor.capture());
        
        // Then
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

        verify(entityManager).remove(userCaptor.capture());
        
        // Then
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

        verify(entityManager).merge(userCaptor.capture());
        
        // Then
        assertEquals(expected, userCaptor.getValue());
    }
    
    @Test
    public void testGetUserById() {
        // Given
        long userId = 1;
        User expected = new User();
        expected.setId(userId);
        
        // When
        when(entityManager.find(Matchers.<Class<User>>any(), anyLong())).thenReturn(expected);
        User actual = unit.getUserById(userId);
        
        // Then
        verify(entityManager).find(User.class, userId);
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
        
        // When
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<User>>any())).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expected);
        User actual = unit.getUserByLoginAndPassword(login, password);
        
        // Then
        verify(entityManager).createNamedQuery(Queries.FIND_USER_BY_LOGIN_AND_PASS.getQueryName(), User.class);
        verify(typedQuery).setParameter("login", login);
        verify(typedQuery).setParameter("password", password);
        verify(typedQuery).getSingleResult();
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
        verify(entityManager).persist(userCaptor.capture());
        
        assertEquals(user, userCaptor.getValue());
    }
    
    @Test
    public void testGetUsers() {
        // Given
        int offset = 20;
        int limit = 0;
        long count = 100;
        User user1 = new User();
        List<User> entities = Arrays.asList(user1);
        PagedResult<User> expected = new PagedResult<User>(offset, limit, count, entities);
        Queries query = Queries.FIND_USERS;
        String querySql = String.format(COUNT_QUERY, query.getQuery()).replace(TABLE_TOKEN, User.class.getSimpleName());
        
        // When
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<User>>any())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(entities);
        when(entityManager.createQuery(anyString(), Matchers.<Class<Long>>any())).thenReturn(longTypedQuery);
        when(longTypedQuery.getSingleResult()).thenReturn(count);
        
        PagedResult<User> actual = unit.getUsers(offset, limit);
        
        // Then
        verify(entityManager).createNamedQuery(query.getQueryName(), User.class);
        verify(entityManager).createQuery(querySql, Long.class);
        verify(typedQuery, times(0)).setParameter(anyString(), anyObject());
        verify(longTypedQuery, times(0)).setParameter(anyString(), anyObject());
        verify(typedQuery).setFirstResult(offset);
        verify(typedQuery).setMaxResults(limit);
        assertEquals(expected, actual);
    }
}
