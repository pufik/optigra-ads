package org.optigra.ads.facade.converter.user;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.user.UserResource;
import org.optigra.ads.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    private final Converter<User, UserResource> unit = new UserConverter();

    @Test
    public void testConvert() {
        // Given
        Long userId = 1L;
        String login = "login";
        String email = "email";
        String fullName = "fullName";

        User user = new User();
        user.setId(userId);
        user.setLogin(login);
        user.setEmail(email);
        user.setFullName(fullName);

        UserResource expected = new UserResource();
        expected.setId(userId);
        expected.setLogin(login);
        expected.setEmail(email);
        expected.setFullName(fullName);

        // When
        UserResource actual = unit.convert(user);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    public void testConvertAll() {
        // Given
        Long userId = 1L;
        String login = "login";
        User user = new User();
        String email = "email";
        String fullName = "fullName";

        user.setId(userId);
        user.setLogin(login);
        user.setEmail(email);
        user.setFullName(fullName);
        List<User> users = Arrays.asList(user);

        UserResource expected = new UserResource();
        expected.setId(userId);
        expected.setLogin(login);
        expected.setEmail(email);
        expected.setFullName(fullName);
        List<UserResource> expecteds = Arrays.asList(expected);

        // When
        List<UserResource> actuals = unit.convertAll(users);

        // Then
        assertEquals(expecteds, actuals);
    }
}
