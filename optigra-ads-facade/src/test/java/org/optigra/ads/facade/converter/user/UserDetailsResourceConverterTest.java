package org.optigra.ads.facade.converter.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.model.user.User;

public class UserDetailsResourceConverterTest {

    private UserDetailsResourceConverter unit = new UserDetailsResourceConverter();
    
    @Test
    public void testConvert() {
        // Given
        Long id = 1L;
        String login = "login";
        String password = "password";
        UserDetailsResource userResource = new UserDetailsResource();
        userResource.setId(id);
        userResource.setLogin(login);
        userResource.setPassword(password);
        
        User expected = new User();
        expected.setId(id);
        expected.setLogin(login);
        expected.setPassword(password);
        
        // When
        User actual = unit.convert(userResource);
        
        // Then
        assertEquals(expected, actual);
    }
}
