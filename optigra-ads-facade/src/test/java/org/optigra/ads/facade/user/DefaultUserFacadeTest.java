package org.optigra.ads.facade.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.user.User;
import org.optigra.ads.service.user.UserService;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultUserFacadeTest {

    @Captor
    private ArgumentCaptor<User> userCaptor;
    
    @Mock
    private Converter<UserDetailsResource, User> userDetailsResourceConverter;
    
    @Mock
    private UserService userService;
    
    @Mock
    private Converter<User, UserResource> userConverter;
    
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
        
        UserResource actual = unit.getUserById(userId);
        
        // Then
        verify(userService).getUserById(userId);
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
        
        unit.createUser(userResource);
        
        verify(userService).createUser(userCaptor.capture());
        
        // Then
        assertEquals(user, userCaptor.getValue());
    }
}
