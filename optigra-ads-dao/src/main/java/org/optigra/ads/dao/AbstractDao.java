package org.optigra.ads.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * Abstract Dao class, that will be responsible for basic operations.
 * @date Jan 23, 2014
 * @author ivanursul
 * @param <E> Entity
 * @param <K> Unique Identifier.
 */
public abstract class AbstractDao<E, K> implements Dao<E, K> {

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
}
