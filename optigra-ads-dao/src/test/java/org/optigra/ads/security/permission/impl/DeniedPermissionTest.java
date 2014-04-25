package org.optigra.ads.security.permission.impl;

import org.junit.Test;
import org.optigra.ads.security.exception.PermissionDeniedException;

public class DeniedPermissionTest {

    private final DeniedPermission unit = new DeniedPermission();

    @Test(expected = PermissionDeniedException.class)
    public void testCheck() {
        // Given
        PermissionContext<?> context = null;

        // When
        unit.check(context);

        // Then
        // Expected exception
    }
}
