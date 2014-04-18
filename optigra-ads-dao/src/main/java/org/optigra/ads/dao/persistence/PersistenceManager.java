package org.optigra.ads.dao.persistence;

import java.util.List;

import org.optigra.ads.dao.Query;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;

/**
 * Persistence manager.
 *
 * @date Apr 16, 2014
 * @author Iurii Parfeniuk
 * @param <T>
 *            - entity type
 * @param <I>
 *            - entity primary key type
 */
public interface PersistenceManager<T, I> {

    void create(T entity);

    T findById(Class<T> clazz, I id);

    void update(T entity);

    void remove(T entity);

    T executeQuerySingleResult(Query<T> query);

    List<T> executeQuery(Query<T> query);

    PagedResult<T> search(PagedSearch<T> searchRequest);
}
