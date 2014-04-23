package org.optigra.ads.security.permission.impl;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.Query;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.session.Session;

@RunWith(MockitoJUnitRunner.class)
public class EntityOwnerEnhancementPermisionTest {

    private final EntityOwnerEnhancementPermision<Application> unit = new EntityOwnerEnhancementPermision<>();

    @Test
    public void testCheck() throws Exception {
        // Given
        String jpQuery = "SELECT e FROM Application e";
        Map<String, Object> parameters = new HashMap<String, Object>();
        User user = new User();
        user.setRole(UserRole.ADMIN);
        Session session = new Session(user);
        Query<Application> query = new Query<Application>(Application.class, jpQuery, parameters);
        PermissionContext<Application> context = new PermissionContext<>();
        context.setSession(session);
        context.setQuery(query);

        String expectedJpQuery = "SELECT e FROM Application e WHERE e IN(SELECT e FROM Application e) AND e.owner = :owner";
        Map<String, Object> expectedParameters = Collections.<String, Object> singletonMap("owner", user);
        Query<Application> expectedQuery = new Query<Application>(Application.class, expectedJpQuery, expectedParameters);

        // When
        unit.check(context);
        Query<Application> actualQuery = context.getQuery();

        // Then
        assertEquals(expectedQuery, actualQuery);
    }
}
