package org.optigra.ads.facade.user;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.dto.UserResource;
import org.optigra.ads.facade.user.DefaultUserFacade;
import org.optigra.ads.model.user.User;
import org.optigra.ads.service.user.UserService;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultUserFacadeTest {

    @Mock
    private UserService defaultUserService;
    
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
        when(defaultUserService.getUserById(anyLong())).thenReturn(user);
        when(userConverter.convert(any(User.class))).thenReturn(expected);
        
        UserResource actual = unit.getUserById(userId);
        
        // Then
        verify(defaultUserService).getUserById(userId);
        verify(userConverter).convert(user);
        
        assertEquals(expected, actual);
    }
    
}
