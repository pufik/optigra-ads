package org.optigra.ads.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.query.Query;

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

    @Resource(name = "persistenceManager")
    private PersistenceManager<E, K> persistenceManager;

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
        return persistenceManager.findById(getEntityClass(), key);
    }

    @Override
    public void create(final E entity) {
        persistenceManager.create(entity);
    }

    @Override
    public void remove(final E entity) {
        persistenceManager.remove(entity);
    }

    @Override
    public void removeById(final K key) {
        E entity = findById(key);
        remove(entity);
    }

    @Override
    public void update(final E entity) {
        persistenceManager.update(entity);
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
    protected PagedResult<E> search(final PagedSearch<E> search) {
        return persistenceManager.search(search);
    }


    /**
     * Single Result Method for executing Named Query.
     *
     * @date Feb 6, 2014
     * @author ivanursul
     * @param query
     *            NamedQuery name
     * @param parameters
     *            query parameters
     * @return entity
     */
    protected E executeSingleResultQuery(final String query, final Map<String, Object> parameters) {
        Query<E> jpQuery = new Query<>(getEntityClass(), query, parameters);

        return persistenceManager.executeQuerySingleResult(jpQuery);
    }
}
