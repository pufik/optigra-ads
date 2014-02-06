package org.optigra.ads.dao.user;


import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
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
    public void testGetUsers() {
        // Given
        String query = "from User";
        long userId = 1;
        User user = new User();
        user.setId(userId);
        List<User> expecteds = Arrays.asList(user);
        int start = 1;
        int length = 20;
        
        // When
        when(entityManager.createQuery(anyString(), Matchers.<Class<User>>any())).thenReturn(typedQuery);
        when(typedQuery.setFirstResult(anyInt())).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(anyInt())).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(expecteds);
        List<User> actuals = unit.getUsers(start, length); 
        
        // Then
        verify(entityManager).createQuery(query, User.class);
        verify(typedQuery).setMaxResults(length);
        verify(typedQuery).setFirstResult(start);
        verify(typedQuery).getResultList();
        assertEquals(expecteds, actuals);
    }
}
