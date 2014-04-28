package org.optigra.ads.security.permission.impl;

import org.optigra.ads.model.query.Queries;
import org.optigra.ads.security.permission.Permission;

/**
 * Permission check for authentication query.
 *
 * @date Apr 23, 2014
 * @author Iurii Parfeniuk
 */
public class AuthenticateUserPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    @Override
    public void check(final PermissionContext<?> context) {
        if (!Queries.FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY.equals(context.getQuery().getQuery())) {
            nextPermision.check(context);
        }
    }

    public Permission<PermissionContext<?>> getNextPermision() {
        return nextPermision;
    }

    public void setNextPermision(final Permission<PermissionContext<?>> nextPermision) {
        this.nextPermision = nextPermision;
    }
}
