package org.optigra.ads.security.permission.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permission.Permission;

@RunWith(MockitoJUnitRunner.class)
public class TransperentQueryPermissionTest {

    @Mock
    private Permission<PermissionContext<?>> nextPermision;

    @InjectMocks
    private final TransperentQueryPermission unit = new TransperentQueryPermission();

    @Test
    public void testCheck() throws Exception {
        // Given
        Query<User> query = new Query<User>(User.class, Queries.FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY, Collections.<String, Object> emptyMap());
        PermissionContext<User> context = new PermissionContext<>();
        context.setQuery(query);

        // When
        unit.check(context);

        // Then
        verifyZeroInteractions(nextPermision);
    }

    @Test
    public void testCheckWhenNextPermission() throws Exception {
        // Given
        Query<User> query = new Query<User>(User.class, Queries.FIND_APPLICATIONS_QUERY, Collections.<String, Object> emptyMap());
        PermissionContext<User> context = new PermissionContext<>();
        context.setQuery(query);

        // When
        unit.check(context);

        // Then
        verify(nextPermision).check(context);
    }
}
