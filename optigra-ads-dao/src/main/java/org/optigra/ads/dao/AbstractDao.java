package org.optigra.ads.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.user.UserRole;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;

/**
 * Abstract Dao class, that will be responsible for basic operations.
 *
 * @date Jan 23, 2014
 * @author ivanursul
 * @param <E>
 *            Entity
 * @param <K>
 *            Unique Identifier.
 */
public abstract class AbstractDao<E, K> implements Dao<E, K> {

    private static final String SECURED_QUERY = "SELECT e FROM %s e WHERE e IN(%s) AND e.owner = :owner";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM %s a WHERE a IN(%s) ";
    private static final String OWNER = "owner";

    @PersistenceContext
    private EntityManager entityManager;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    /**
     * Entity class method.
     *
     * @date Jan 24, 2014
     * @author ivanursul
     * @return entity class for custom dao.
     */
    protected abstract Class<E> getEntityClass();

    @Override
    public E findById(final K key) {
        return entityManager.find(getEntityClass(), key);
    }

    @Override
    public void create(final E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(final E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void removeById(final K key) {
        E entity = findById(key);
        remove(entity);
    }

    @Override
    public void update(final E entity) {
        entityManager.merge(entity);
    }

    /**
     * Search function for named query.
     *
     * @date Feb 6, 2014
     * @author ivanursul
     * @param search
     *            Paged object
     * @return List of entities
     */
    protected PagedResult<E> search(final PagedSearch search) {
        TypedQuery<E> query = createSecuredQuery(search.getQuery().getQuery(), search.getParameters(), getEntityClass());
        query.setFirstResult(search.getOffset());
        query.setMaxResults(search.getLimit());

        Long count = getQueryCount(search.getQuery().getQuery(), search.getParameters(), getEntityClass());

        PagedResult<E> result = new PagedResult<>(search.getOffset(), search.getLimit(), count, query.getResultList());

        return result;
    }

    /**
     * Method, that will return count of rows in query.
     *
     * @date Feb 10, 2014
     * @author ivanursul
     * @param search
     * @param parameters
     * @param querySql
     * @return count of rows.
     */
    private <M> Long getQueryCount(final String querySql, final Map<String, Object> parameters, final Class<M> clazz) {
        // TODO: IP - Security check should be moved to special class
        Session session = sessionService.getCurrentSession();
        String jpQuery = querySql;
        Map<String, Object> queryParameters = new HashMap<>(parameters);

        // TODO: IP - Duplicated stuff
        if (!UserRole.ADMIN.equals(session.getUser().getRole())) {
            jpQuery = getSecuredQuery(querySql, clazz);
            queryParameters.put(OWNER, session.getUser());
        }

        jpQuery = String.format(COUNT_QUERY, clazz.getSimpleName(), jpQuery);

        TypedQuery<Long> countQuery = createQuery(jpQuery, queryParameters, Long.class);

        return countQuery.getSingleResult();
    }

    protected List<E> executeNamedQuery(final String queryName, final Map<String, Object> parameters) {
        TypedQuery<E> query = createNamedQuery(queryName, parameters);

        return query.getResultList();
    }

    /**
     * Single Result Method for executing Named Query.
     *
     * @date Feb 6, 2014
     * @author ivanursul
     * @param queryName
     *            NamedQuery name
     * @param parameters
     *            query parameters
     * @return entity
     */
    protected E executeSingleResultNamedQuery(final String queryName, final Map<String, Object> parameters) {
        E entity = null;

        TypedQuery<E> query = entityManager.createNamedQuery(queryName, getEntityClass());

        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }

        entity = query.getSingleResult();

        return entity;
    }

    private TypedQuery<E> createNamedQuery(final String queryName, final Map<String, Object> parameters) {
        TypedQuery<E> query = entityManager.createNamedQuery(queryName, getEntityClass());

        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }

        return query;
    }

    private <M> TypedQuery<M> createSecuredQuery(final String querySql, final Map<String, Object> parameters, final Class<M> clazz) {
        // TODO: IP - Security check should be moved to special class
        Session session = sessionService.getCurrentSession();
        String jpQuery = querySql;
        Map<String, Object> queryParameters = new HashMap<>(parameters);

        if (!UserRole.ADMIN.equals(session.getUser().getRole())) {
            jpQuery = getSecuredQuery(querySql, clazz);
            queryParameters.put(OWNER, session.getUser());
        }

        return createQuery(jpQuery, queryParameters, clazz);
    }

    private String getSecuredQuery(final String query, final Class<?> clazz) {
        return String.format(SECURED_QUERY, clazz.getSimpleName(), query);
    }

    private <M> TypedQuery<M> createQuery(final String querySql, final Map<String, Object> parameters, final Class<M> clazz) {
        TypedQuery<M> countQuery = entityManager.createQuery(querySql, clazz);

        for (String key : parameters.keySet()) {
            countQuery.setParameter(key, parameters.get(key));
        }

        return countQuery;
    }
}
