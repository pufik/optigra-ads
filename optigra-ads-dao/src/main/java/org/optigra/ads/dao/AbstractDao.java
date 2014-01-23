package org.optigra.ads.dao;

import java.lang.reflect.ParameterizedType;

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
    private Class<E> entityClass;

    /**
     * Base constructor.
     * @date Jan 23, 2014
     * @author ivanursul
     */
    @SuppressWarnings("unchecked")
    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public E findById(final K key) {
        return entityManager.find(entityClass, key);
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
