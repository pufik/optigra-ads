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
 * @date Jan 23, 2014
 * @author ivanursul
 * @param <E> Entity
 * @param <K> Unique Identifier.
 */
public abstract class AbstractDao<E, K> implements Dao<E, K> {
    private static final String COUNT_QUERY = "select count(*) from %s";

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Entity class method.
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
    public void persist(final E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(final E entity) {
        entityManager.remove(entity);
    }

    @Override
    public void update(final E entity) {
        entityManager.merge(entity);
    }

    /**
     * Search function for named query.
     * @date Feb 6, 2014
     * @author ivanursul
     * @param search Paged object
     * @return List of entities
     */
    protected PagedResult<E> search(final PagedSearch search) {
        TypedQuery<E> query = entityManager.createNamedQuery(search.getNamedQuery(), getEntityClass());

        Map<String, Object> parameters = search.getParameters();
        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }

        query.setFirstResult(search.getStart());
        query.setMaxResults(search.getOffset());

        String querySql = String.format(COUNT_QUERY, getEntityClass().getSimpleName());
        Integer count = (Integer) entityManager.createQuery(querySql).getSingleResult();

        PagedResult<E> result = new PagedResult<>(search.getStart(), search.getOffset(), count, query.getResultList());
        return result;
    }

    /**
     * Method List of entities for named query.
     * @date Feb 6, 2014
     * @author ivanursul
     * @param queryName NamedQuery name
     * @param parameters query parameters
     * @return List of entities
     */
    protected List<E> executeNamedQuery(final String queryName, final Map<String, Object> parameters) {
        TypedQuery<E> query = entityManager.createNamedQuery(queryName, getEntityClass());

        for (String key : parameters.keySet()) {
            query.setParameter(key, parameters.get(key));
        }

        return query.getResultList();
    }

    /**
     * Single Result Method for executing Named Query.
     * @date Feb 6, 2014
     * @author ivanursul
     * @param queryName NamedQuery name
     * @param parameters query parameters
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
}
