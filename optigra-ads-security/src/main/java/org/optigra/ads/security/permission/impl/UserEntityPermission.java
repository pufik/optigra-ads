package org.optigra.ads.security.permission.impl;

import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permission.Permission;

public class UserEntityPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    @Override
    public void check(final PermissionContext<?> context) {
        if (!User.class.getName().equals(context.getQuery().getEntityClass().getName())) {
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
