package org.optigra.ads.security.permission.impl;

import org.optigra.ads.security.permission.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Empty permission checker
 * @date Apr 18, 2014
 * @author Iurii Parfeniuk
 *
 */
public class EmptyPermission implements Permission<PermissionContext<?>>{

    private static final Logger LOG = LoggerFactory.getLogger(EmptyPermission.class);

    @Override
    public void check(final PermissionContext<?> context) {
        LOG.info("Empty check for: " + context);
    }
}
