package org.optigra.ads.facade.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.user.UserService;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultUserFacadeTest {

    @Captor
    private ArgumentCaptor<PagedResult<User>> pagedResultCaptor;
    
    @Captor
    private ArgumentCaptor<PagedResultResource<UserResource>> pagedResultResourceCaptor;
    
    @Captor
    private ArgumentCaptor<User> userCaptor;
    
    @Mock
    private Converter<UserDetailsResource, User> userDetailsResourceConverter;
    
    @Mock
    private Converter<UserDetailsResource, User> putUserDetailsResourceConverter;
    
    @Mock
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;
    
    @Mock
    private UserService userService;
    
    @Mock
    private SessionService sessionService;
    
    @Mock
    private Converter<User, UserResource> userConverter;
    
    @Mock
    private Session session;

    @InjectMocks
    private DefaultUserFacade unit = new DefaultUserFacade();
    
    @Test
    public void testGetUserById() {
        // Given
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        UserResource expected = new UserResource();
        
        // When
        when(userService.getUserById(anyLong())).thenReturn(user);
        when(userConverter.convert(any(User.class))).thenReturn(expected);
        
        UserResource actual = unit.getUser(userId);
        
        // Then
        verify(userService).getUserById(userId);
        verify(userConverter).convert(user);
        
        assertEquals(expected, actual);
    }

    @Test
    public void testGetCurrentUser() {
    	// Given
    	Long userId = 1L;
    	
    	User user = new User();
    	user.setId(userId);
    	
    	UserResource expected = new UserResource();

    	// When
    	when(sessionService.getCurrentSession()).thenReturn(session);
    	when(session.getUser()).thenReturn(user);
    	when(userConverter.convert(any(User.class))).thenReturn(expected);
    	
    	UserResource actual = unit.getCurrentUser();
    	
    	// Then
    	verify(sessionService).getCurrentSession();
    	verify(session).getUser();
    	verify(userConverter).convert(user);
    	
    	assertEquals(expected, actual);
    }
    
    @Test
    public void testCreateUser() {
        // Given
        Long id = 1L; 
        String login = "login";
        String password = "pass";
        
        UserDetailsResource userResource = new UserDetailsResource();
        userResource.setId(id);
        userResource.setLogin(login);
        userResource.setPassword(password);

        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setPassword(password);
        
        // When
        when(userDetailsResourceConverter.convert(any(UserDetailsResource.class))).thenReturn(user);
        when(userConverter.convert(any(User.class))).thenReturn(userResource);
        
        UserResource actual = unit.createUser(userResource);
        
        // Then
        verify(userService).createUser(user);
        assertEquals(userResource, actual);
    }
    
    @Test
    public void testGetUsers() {
        // Given
        int offset = 1;
        int limit = 23;
        long count = 100;
        UserResource resource1 = new UserResource();
        List<UserResource> userResources = Arrays.asList(resource1 );
        PagedResultResource<UserResource> expected = new PagedResultResource<>(ResourceUri.USER);
        expected.setEntities(userResources);
        User user1 = new User();
        List<User> entities = Arrays.asList(user1 );
        PagedResult<User> pagedResult = new PagedResult<User>(offset, limit, count, entities );
        // When
        when(userService.getUsers(anyInt(), anyInt())).thenReturn(pagedResult);
        when(userConverter.convertAll(anyListOf(User.class))).thenReturn(userResources);
        
        PagedResultResource<UserResource> actual = unit.getUsers(offset, limit);
        
        // Then
        verify(userService).getUsers(offset, limit);
        verify(userConverter).convertAll(entities);
        verify(pagedSearchConverter).convert(pagedResultCaptor.capture(), pagedResultResourceCaptor.capture());
        assertEquals(pagedResult, pagedResultCaptor.getValue());
        assertEquals(expected, pagedResultResourceCaptor.getValue());
        assertEquals(expected, actual);
    }
    
    @Test
	public void testUpdateUser() throws Exception {
		// Given
    	Long userId = 1L;
    	String email = "email";
    	
    	UserDetailsResource userResource = new UserDetailsResource();
		userResource.setEmail(email);

		User user = new User();
		user.setEmail(email);
		
		// When
		when(userService.getUserById(anyLong())).thenReturn(user);
		
    	unit.updateUser(userId, userResource);

		// Then
    	verify(putUserDetailsResourceConverter).convert(userResource, user);
    	verify(userService).update(user);
	}
    
    @Test
	public void testDeleteUser() throws Exception {
		// Given
    	Long userId = 1L;
		
		// When
    	unit.deleteUser(userId);

		// Then
    	verify(userService).deleteUser(userId);
	}
}
