package org.optigra.ads.security.permission.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.permission.Permission;
import org.optigra.ads.security.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class AdminPermissionTest {

    @Mock
    private Permission<PermissionContext<?>> nextPermision;

    @InjectMocks
    private final AdminPermission unit = new AdminPermission();

    @Test
    public void testCheckWhenAdminRole() throws Exception {
        // Given
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Session session = new Session(user);
        PermissionContext<?> context = new PermissionContext<>();
        context.setSession(session);

        // When
        unit.check(context);

        // Then
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckWhenUserRole() throws Exception {
        // Given
        User user = new User();
        user.setRole(UserRole.USER);
        Session session = new Session(user);
        PermissionContext<?> context = new PermissionContext<>();
        context.setSession(session);

        // When
        unit.check(context);

        // Then
        verify(nextPermision).check(context);
    }
}
