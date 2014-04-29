package org.optigra.ads.security.permission.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
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
        User user = new User();
        user.setRole(UserRole.ADMIN);
        PermissionContext<User> context = new PermissionContext<>();
        context.setSession(new Session(user));
        context.setEntity(user);

        // When
        unit.check(context);

        // Then
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckWhenNotUserEntity() throws Exception {
        // Given
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Application application = new Application();
        PermissionContext<Application> context = new PermissionContext<>();
        context.setSession(new Session(user));
        context.setEntity(application);

        // When
        unit.check(context);

        // Then
        verify(nextPermision).check(context);
    }
}
