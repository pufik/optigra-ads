package org.optigra.ads.security.permission.impl;

import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permission.Permission;

/**
 * User Entity check permission.
 *
 * @date Apr 29, 2014
 * @author Iurii Parfeniuk
 */
public class UserEntityPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    @Override
    public void check(final PermissionContext<?> context) {
        User user = context.getSession().getUser();

        if (!user.equals(context.getEntity())) {
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
