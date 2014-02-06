package org.optigra.ads.dao.pagination;

import java.util.List;


/**
 * Class for result with pagination.
 * @date Feb 6, 2014
 * @author ivanursul
 * @param <E> Entity
 */
public class PagedResult<E> {

    private int start;
    private int offset;
    private long count;
    private List<E> entities;

    /**
     * Constructor for all fields.
     * @date Feb 6, 2014
     * @author ivanursul
     * @param start start position.
     * @param offset length.
     * @param count count(*)
     * @param entities list of entities.
     */
    public PagedResult(final int start, final int offset, final long count, final List<E> entities) {
        this.start = start;
        this.offset = offset;
        this.count = count;
        this.entities = entities;
    }

    public int getStart() {
        return start;
    }

    public void setStart(final int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(final int offset) {
        this.offset = offset;
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
        result = prime * result
                + ((entities == null) ? 0 : entities.hashCode());
        result = prime * result + offset;
        result = prime * result + start;
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
        PagedResult<?> other = (PagedResult<?>) obj;
        if (count != other.count)
            return false;
        if (entities == null) {
            if (other.entities != null)
                return false;
        } else if (!entities.equals(other.entities))
            return false;
        if (offset != other.offset)
            return false;
        if (start != other.start)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagedResult [start=" + start + ", offset=" + offset
                + ", count=" + count + ", entities=" + entities + "]";
    }
    
    
}
