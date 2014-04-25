package org.optigra.ads.security.permission.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.Query;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.permission.Permission;
import org.optigra.ads.security.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityEnhancementPermissionTest {

    @Mock
    private Permission<PermissionContext<?>> nextPermision;

    @InjectMocks
    private final UserEntityEnhancementPermission unit = new UserEntityEnhancementPermission();

    @Test
    public void testCheck() throws Exception {
        // Given
        String jpQuery = "SELECT u FROM User u";
        Map<String, Object> parameters = new HashMap<String, Object>();
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Session session = new Session(user);
        Query<User> query = new Query<User>(User.class, jpQuery, parameters);
        PermissionContext<User> context = new PermissionContext<>();
        context.setSession(session);
        context.setQuery(query);

        String expectedJpQuery = "SELECT u FROM User u WHERE u IN(SELECT u FROM User u) AND u = :user";
        Map<String, Object> expectedParameters = Collections.<String, Object> singletonMap("user", user);
        Query<User> expectedQuery = new Query<User>(User.class, expectedJpQuery, expectedParameters);

        // When
        unit.check(context);
        Query<User> actualQuery = context.getQuery();

        // Then
        assertEquals(expectedQuery, actualQuery);
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckNextPermission() throws Exception {
        // Given
        String jpQuery = "SELECT u FROM User u";
        Map<String, Object> parameters = new HashMap<String, Object>();
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Session session = new Session(user);
        Query<Application> query = new Query<Application>(Application.class, jpQuery, parameters);
        PermissionContext<Application> context = new PermissionContext<>();
        context.setSession(session);
        context.setQuery(query);

        // When
        unit.check(context);

        // Then
        verify(nextPermision).check(context);
    }
}
