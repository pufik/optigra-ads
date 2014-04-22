package org.optigra.ads.security.permision.impl;

import org.optigra.ads.model.Model;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permision.Permission;

/**
 * Entity owner permission check
 *
 * @date Apr 16, 2014
 * @author Iurii Parfeniuk
 */
public class OwnerPermission implements Permission<PermissionContext<? extends Model>> {

    private Permission<PermissionContext<?>> nextPermision;

    @Override
    public void check(final PermissionContext<? extends Model> context) {
        User user = context.getSession().getUser();

        if (!user.equals(context.getEntity().getOwner())) {
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
