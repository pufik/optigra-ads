package org.optigra.ads.security.permision.impl;

import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.permision.Permission;

/**
 * Administrator permission check
 *
 * @date Apr 16, 2014
 * @author Iurii Parfeniuk
 */
public class AdminPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    @Override
    public void check(final PermissionContext<?> context) {
        User user = context.getSession().getUser();

        if (!UserRole.ADMIN.equals(user.getRole())) {
            getNextPermision().check(context);
        }
    }

    public Permission<PermissionContext<?>> getNextPermision() {
        return nextPermision;
    }

    public void setNextPermision(final Permission<PermissionContext<?>> nextPermision) {
        this.nextPermision = nextPermision;
    }
}
