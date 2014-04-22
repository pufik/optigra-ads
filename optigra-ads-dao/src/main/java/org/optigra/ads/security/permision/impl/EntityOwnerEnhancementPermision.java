package org.optigra.ads.security.permision.impl;

import org.optigra.ads.dao.Query;
import org.optigra.ads.model.Model;
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.permision.Permission;

public class EntityOwnerEnhancementPermision implements Permission<PermissionContext<Model>> {

    private static final String SECURED_QUERY = "SELECT e FROM %s e WHERE e IN(%s) AND e.owner = :owner";
    private static final String OWNER = "owner";

    @Override
    public void check(final PermissionContext<Model> context) {
        Query<Model> query = context.getQuery();
        String originalQuery = query.getQuery();
        User currentUser = context.getSession().getUser();

        String updatedQuery = getEnhancedQuery(originalQuery, query.getEntityClass());

        query.setQuery(updatedQuery);
        query.getParameters().put(OWNER, currentUser);
        context.setQuery(query);
    }

    private String getEnhancedQuery(final String query, final Class<?> clazz) {
        return String.format(SECURED_QUERY, clazz.getSimpleName(), query);
    }
}
