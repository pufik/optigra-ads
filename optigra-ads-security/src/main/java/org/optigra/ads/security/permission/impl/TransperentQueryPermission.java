package org.optigra.ads.security.permission.impl;

import java.util.HashSet;
import java.util.Set;

import org.optigra.ads.model.query.Queries;
import org.optigra.ads.security.permission.Permission;

/**
 * Permission check for authentication query.
 *
 * @date Apr 23, 2014
 * @author Iurii Parfeniuk
 */
public class TransperentQueryPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    private static final Set<String> transperentQueries;
    static {
        transperentQueries = new HashSet<>();
        transperentQueries.add(Queries.FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
        transperentQueries.add(Queries.FIND_DEVICE_BY_UID_AND_APPLICATION_QUERY);
    }

    @Override
    public void check(final PermissionContext<?> context) {
        if (!transperentQueries.contains(context.getQuery().getQuery())) {
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
