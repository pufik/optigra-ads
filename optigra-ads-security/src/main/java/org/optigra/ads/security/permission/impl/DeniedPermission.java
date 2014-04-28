package org.optigra.ads.security.permission.impl;

import org.optigra.ads.security.exception.PermissionDeniedException;
import org.optigra.ads.security.permission.Permission;

/**
 * Denied access.
 *
 * @date Apr 18, 2014
 * @author Iurii Parfeniuk
 */
public class DeniedPermission implements Permission<PermissionContext<?>> {

    @Override
    public void check(final PermissionContext<?> context) {
        throw new PermissionDeniedException("Permission denied");
    }
}
