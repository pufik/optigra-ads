package org.optigra.ads.security.permission.impl;

import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permission.Permission;

public class UserEntityPermission implements Permission<PermissionContext<?>> {

    private Permission<PermissionContext<?>> nextPermision;

    private static final String SECURED_QUERY = "SELECT u FROM User u WHERE u IN(%s) AND u = :user";
    private static final String USER = "user";

    @Override
    public void check(final PermissionContext<?> context) {
        if (User.class.getName().equals(context.getQuery().getEntityClass().getName())) {
            String originalQuery = context.getQuery().getQuery();
            User currentUser = context.getSession().getUser();

            String updatedQuery = getEnhancedQuery(originalQuery, context.getQuery().getEntityClass());
            context.getQuery().setQuery(updatedQuery);
            context.getQuery().getParameters().put(USER, currentUser);
        } else {
            nextPermision.check(context);
        }
    }

    private String getEnhancedQuery(final String query, final Class<?> clazz) {
        return String.format(SECURED_QUERY, query);
    }

    public Permission<PermissionContext<?>> getNextPermision() {
        return nextPermision;
    }

    public void setNextPermision(final Permission<PermissionContext<?>> nextPermision) {
        this.nextPermision = nextPermision;
    }
}
