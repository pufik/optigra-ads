package org.optigra.ads.security.permission.impl;

import org.optigra.ads.model.query.Query;
import org.optigra.ads.security.session.Session;

public class PermissionContext<T> {

    private Session session;

    private Query<T> query;

    private T entity;

    public PermissionContext() {
        super();
    }

    public PermissionContext(final Session session, final T entity) {
        super();
        this.session = session;
        this.entity = entity;
    }

    public PermissionContext(final Session session, final Query<T> query) {
        super();
        this.session = session;
        this.query = query;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(final Session session) {
        this.session = session;
    }

    public Query<T> getQuery() {
        return query;
    }

    public void setQuery(final Query<T> query) {
        this.query = query;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(final T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "PermissionContext [session=" + session + ", query=" + query + ", entity=" + entity + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entity == null) ? 0 : entity.hashCode());
        result = prime * result + ((query == null) ? 0 : query.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PermissionContext other = (PermissionContext) obj;
        if (entity == null) {
            if (other.entity != null)
                return false;
        } else if (!entity.equals(other.entity))
            return false;
        if (query == null) {
            if (other.query != null)
                return false;
        } else if (!query.equals(other.query))
            return false;
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        return true;
    }
}
