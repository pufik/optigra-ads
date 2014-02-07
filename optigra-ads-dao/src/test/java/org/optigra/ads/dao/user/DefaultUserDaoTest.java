package org.optigra.ads.dao.user;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.optigra.ads.common.Queries;
import org.optigra.ads.model.user.User;



@RunWith(MockitoJUnitRunner.class)
public class DefaultUserDaoTest {

    @Captor
    private ArgumentCaptor<User> userCaptor;
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery<User> typedQuery;
    
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
        unit.persist(expected);

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
}
