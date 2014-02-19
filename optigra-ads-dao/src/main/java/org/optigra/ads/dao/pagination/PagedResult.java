package org.optigra.ads.dao.pagination;

import java.util.List;

/**
 * Class for result with pagination.
 *
 * @date Feb 6, 2014
 * @author ivanursul
 * @param <E>
 *            Entity
 */
public class PagedResult<E> {

    private int offset;
    private int limit;
    private long count;
    private List<E> entities;

    /**
     * Constructor for all fields.
     *
     * @date Feb 6, 2014
     * @author ivanursul
     * @param offset
     *            start position.
     * @param limit
     *            length.
     * @param count
     *            count(*)
     * @param entities
     *            list of entities.
     */
    public PagedResult(final int offset, final int limit, final long count, final List<E> entities) {
        this.offset = offset;
        this.limit = limit;
        this.count = count;
        this.entities = entities;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(final int limit) {
        this.limit = limit;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    public List<E> getEntities() {
        return entities;
    }

    public void setEntities(final List<E> entities) {
        this.entities = entities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (count ^ (count >>> 32));
        result = prime * result + ((entities == null) ? 0 : entities.hashCode());
        result = prime * result + limit;
        result = prime * result + offset;
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
        PagedResult other = (PagedResult) obj;
        if (count != other.count)
            return false;
        if (entities == null) {
            if (other.entities != null)
                return false;
        } else if (!entities.equals(other.entities))
            return false;
        if (limit != other.limit)
            return false;
        if (offset != other.offset)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagedResult [offset=" + offset + ", limit=" + limit + ", count=" + count + ", entities=" + entities + "]";
    }
}
