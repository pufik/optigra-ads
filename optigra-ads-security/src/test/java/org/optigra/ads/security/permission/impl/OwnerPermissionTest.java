package org.optigra.ads.security.permission.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.Model;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.permission.Permission;
import org.optigra.ads.security.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class OwnerPermissionTest {

    @Mock
    private Permission<PermissionContext<?>> nextPermision;

    @InjectMocks
    private final OwnerPermission unit = new OwnerPermission();

    @Test
    public void testCheckWhenOwner() throws Exception {
        // Given
        User user = new User();
        user.setRole(UserRole.USER);

        Application entity = new Application();
        entity.setOwner(user);
        Session session = new Session(user);

        PermissionContext<Model> context = new PermissionContext<>();
        context.setSession(session);
        context.setEntity(entity);

        // When
        unit.check(context);

        // Then
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckWhenNotOwner() throws Exception {
        // Given
        User owner = new User();
        owner.setRole(UserRole.USER);

        Application entity = new Application();
        entity.setOwner(owner);
        User sessionUser = new User();
        Session session = new Session(sessionUser);

        PermissionContext<Model> context = new PermissionContext<>();
        context.setSession(session);
        context.setEntity(entity);

        // When
        unit.check(context);

        // Then
        verify(nextPermision).check(context);
    }

}
