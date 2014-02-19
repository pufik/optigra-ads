package org.optigra.ads.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;

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

    private static final String TABLE_TOKEN = "$table";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM " + TABLE_TOKEN + " a WHERE a IN(%s) ";

    @PersistenceContext
    private EntityManager entityManager;

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
        TypedQuery<E> query = createNamedQuery(search.getQuery().getQueryName(), search.getParameters());
        query.setFirstResult(search.getOffset());
        query.setMaxResults(search.getLimit());

        Long count = getQueryCount(search);

        PagedResult<E> result = new PagedResult<>(search.getOffset(), search.getLimit(), count, query.getResultList());

        return result;
    }

    /**
     * Method, that will return count of rows in query.
     *
     * @date Feb 10, 2014
     * @author ivanursul
     * @param search
     * @return count of rows.
     */
    private Long getQueryCount(final PagedSearch search) {
        String querySql = String.format(COUNT_QUERY, search.getQuery().getQuery()).replace(TABLE_TOKEN, getEntityClass().getSimpleName());
        TypedQuery<Long> countQuery = createQuery(querySql, search.getParameters());

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

    private TypedQuery<Long> createQuery(final String querySql, final Map<String, Object> parameters) {
        TypedQuery<Long> countQuery = entityManager.createQuery(querySql, Long.class);

        for (String key : parameters.keySet()) {
            countQuery.setParameter(key, parameters.get(key));
        }

        return countQuery;
    }
}
