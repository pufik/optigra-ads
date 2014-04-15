package org.optigra.ads.facade.converter.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;

public class UserDetailsResourceConverterTest {

    private final UserDetailsResourceConverter unit = new UserDetailsResourceConverter();

    @Test
    public void testConvert() {
        // Given
        Long id = 1L;
        String login = "login";
        String password = "password";
        String email = "email";
        String fullName = "fullName";
        String imageUrl = "imageUrl";

        UserDetailsResource userResource = new UserDetailsResource();
        userResource.setId(id);
        userResource.setLogin(login);
        userResource.setPassword(password);
        userResource.setRole(UserRole.USER);
        userResource.setEmail(email);
        userResource.setFullName(fullName);
        userResource.setImageUrl(imageUrl);

        User expected = new User();
        expected.setLogin(login);
        expected.setPassword(password);
        expected.setRole(UserRole.USER);
        expected.setEmail(email);
        expected.setFullName(fullName);
        expected.setImageUrl(imageUrl);

        // When
        User actual = unit.convert(userResource);

        // Then
        assertEquals(expected, actual);
    }
}
