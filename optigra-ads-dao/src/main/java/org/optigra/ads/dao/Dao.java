package org.optigra.ads.dao;


/**
 * Basic CRUD interface with basic operations.
 * @date Jan 23, 2014
 * @author ivanursul
 * @param <E> Entity
 * @param <K> Unique Identifier.
 */
public interface Dao<E, K> {
    /**
     * Method, that will return entity.
     * @date Jan 23, 2014
     * @author ivanursul
     * @param key Unique identifier
     * @return Entity, that will be returned
     */
    E findById(K key);
    /**
     * Saving entity.
     * @date Jan 23, 2014
     * @author ivanursul
     * @param entity Entity to be saved.
     */
    void persist(E entity);
    /**
     * Removing entity.
     * @date Jan 23, 2014
     * @author ivanursul
     * @param entity Entity to be removed.
     */
    void remove(E entity);
    /**
     * Updating entity.
     * @date Jan 23, 2014
     * @author ivanursul
     * @param entity Entity to be updated.
     */
    void update(E entity);
}
