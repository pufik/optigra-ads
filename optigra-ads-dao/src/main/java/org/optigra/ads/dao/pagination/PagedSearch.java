package org.optigra.ads.dao.pagination;

import org.optigra.ads.dao.Query;

/**
 * Class for paged queries.
 *
 * @date Feb 6, 2014
 * @author ivanursul
 */
public class PagedSearch<T> {

    private final int offset;
    private final int limit;
    private Query<T> query;

    /**
     * Constructor with parameters.
     *
     * @date Feb 12, 2014
     * @author ivanursul
     * @param start
     * @param offset
     * @param query
     * @param parameters
     */
    public PagedSearch(final int offset, final int limit, final Query<T> query) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.query = query;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public Query<T> getQuery() {
        return query;
    }

    public void setQuery(final Query<T> query) {
        this.query = query;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + limit;
        result = prime * result + offset;
        result = prime * result + ((query == null) ? 0 : query.hashCode());
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
        PagedSearch other = (PagedSearch) obj;
        if (limit != other.limit)
            return false;
        if (offset != other.offset)
            return false;
        if (query == null) {
            if (other.query != null)
                return false;
        } else if (!query.equals(other.query))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagedSearch [offset=" + offset + ", limit=" + limit + ", query=" + query + "]";
    }
}
