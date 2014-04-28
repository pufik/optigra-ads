package org.optigra.ads.security.permission.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EmptyPermissionTest {

    private final EmptyPermission unit = new EmptyPermission();

    @Test
    public void testCheck() {

        // Given
        PermissionContext<?> context = new PermissionContext<>();

        // When
        unit.check(context);

        // Then
        assertEquals(context, context);
    }
}
