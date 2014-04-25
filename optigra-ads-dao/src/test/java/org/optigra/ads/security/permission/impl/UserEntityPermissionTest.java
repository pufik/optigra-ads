package org.optigra.ads.security.permission.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

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
public class UserEntityPermissionTest {

    @Mock
    private Permission<PermissionContext<?>> nextPermision;

    @InjectMocks
    private final UserEntityPermission unit = new UserEntityPermission();

    @Test
    public void testCheckWhenUserEntity() throws Exception {
        // Given
        String jpQuery = "QUERY";
        Map<String, Object> parameters = new HashMap<String, Object>();
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Session session = new Session(user);
        Query<User> query = new Query<User>(User.class, jpQuery, parameters);
        PermissionContext<User> context = new PermissionContext<>();
        context.setSession(session);
        context.setQuery(query);

        // When
        unit.check(context);

        // Then
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckWhenNotUserEntity() throws Exception {
        // Given
        String jpQuery = "QUERY";
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
