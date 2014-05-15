package org.optigra.ads.dao.persistence;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.pagination.PagedSearch;
import org.optigra.ads.model.query.Query;
import org.optigra.ads.security.permission.Permission;
import org.optigra.ads.security.permission.impl.PermissionContext;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository("persistenceManager")
public class SecuredPersistenceManager<T, I> implements PersistenceManager<T, I> {

    private static final Logger LOG = LoggerFactory.getLogger(SecuredPersistenceManager.class);

    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM %s a WHERE a IN(%s) ";

    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "ownerPermission")
    private Permission<PermissionContext<T>> ownerPermision;

    @Resource(name = "queryPermission")
    private Permission<PermissionContext<T>> queryPermision;

    @Override
    public void create(final T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T findById(final Class<T> clazz, final I id) {
        T entity = entityManager.find(clazz, id);

        PermissionContext<T> context = new PermissionContext<>(getSession(), entity);

        LOG.info(String.format("Check permission for entity [%s] with permission context [%s] ", entity, context));

        ownerPermision.check(context);

        return entity;
    }

    @Override
    public void update(final T entity) {
        PermissionContext<T> context = new PermissionContext<>(getSession(), entity);

        LOG.info(String.format("Check permission for entity [%s] with permission context [%s] ", entity, context));

        ownerPermision.check(context);

        entityManager.merge(entity);
    }

    @Override
    public void remove(final T entity) {
        PermissionContext<T> context = new PermissionContext<>(getSession(), entity);

        LOG.info(String.format("Check permission for entity [%s] with permission context [%s] ", entity, context));

        ownerPermision.check(context);

        entityManager.remove(entity);

    }

    @Override
    public T executeQuerySingleResult(final Query<T> query) {
        PermissionContext<T> context = new PermissionContext<>(getSession(), query);

        LOG.info(String.format("Check permission for query [%s] with permission context [%s] ", query, context));

        queryPermision.check(context);

        Query<T> finalQuery = context.getQuery();
        TypedQuery<T> typedQuery = createQuery(finalQuery.getQuery(), finalQuery.getParameters(), finalQuery.getEntityClass());

        return typedQuery.getSingleResult();
    }

    @Override
    public List<T> executeQuery(final Query<T> query) {
        PermissionContext<T> context = new PermissionContext<>(getSession(), query);

        LOG.info(String.format("Check permission for query [%s] with permission context [%s] ", query, context));

        queryPermision.check(context);

        Query<T> finalQuery = context.getQuery();
        TypedQuery<T> typedQuery = createQuery(finalQuery.getQuery(), finalQuery.getParameters(), finalQuery.getEntityClass());

        return typedQuery.getResultList();
    }

    @Override
    public PagedResult<T> search(final PagedSearch<T> searchRequest) {
        PermissionContext<T> context = new PermissionContext<>(getSession(), searchRequest.getQuery());

        LOG.info(String.format("Check permission for search [%s] with permission context [%s] ", searchRequest, context));

        queryPermision.check(context);

        Query<T> finalQuery = context.getQuery();
        TypedQuery<T> typedQuery = createQuery(finalQuery.getQuery(), finalQuery.getParameters(), finalQuery.getEntityClass());
        typedQuery.setFirstResult(searchRequest.getOffset());
        typedQuery.setMaxResults(searchRequest.getLimit());

        Long count = getQueryCount(finalQuery.getQuery(), finalQuery.getParameters(), finalQuery.getEntityClass());

        PagedResult<T> result = new PagedResult<>(searchRequest.getOffset(), searchRequest.getLimit(), count, typedQuery.getResultList());

        return result;
    }

    private <M> Long getQueryCount(final String querySql, final Map<String, Object> parameters, final Class<M> clazz) {

        String jpQuery = String.format(COUNT_QUERY, clazz.getSimpleName(), querySql);

        TypedQuery<Long> countQuery = createQuery(jpQuery, parameters, Long.class);

        return countQuery.getSingleResult();
    }

    private <M> TypedQuery<M> createQuery(final String querySql, final Map<String, Object> parameters, final Class<M> clazz) {
        TypedQuery<M> typedQuery = entityManager.createQuery(querySql, clazz);

        LOG.info(String.format("Create JPQL: query=[%s]; parameters=[%s] ", querySql, parameters));

        for (String key : parameters.keySet()) {
            typedQuery.setParameter(key, parameters.get(key));
        }

        return typedQuery;
    }

    protected Session getSession() {
        return sessionService.getCurrentSession();
    }
}
