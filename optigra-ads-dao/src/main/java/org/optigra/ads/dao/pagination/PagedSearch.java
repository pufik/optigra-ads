package org.optigra.ads.dao.pagination;

import java.util.Map;

import org.optigra.ads.model.Queries;

/**
 * Class for paged queries.
 *
 * @date Feb 6, 2014
 * @author ivanursul
 */
public class PagedSearch {

    private final int offset;
    private final int limit;
    private Queries query;
    private Map<String, Object> parameters;

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
    public PagedSearch(final int offset, final int limit, final Queries query, final Map<String, Object> parameters) {
        super();
        this.offset = offset;
        this.limit = limit;
        this.query = query;
        this.parameters = parameters;
    }

    public int getOffset() {
        return offset;
    }

    public int getLimit() {
        return limit;
    }

    public Queries getQuery() {
        return query;
    }

    public void setQuery(final Queries query) {
        this.query = query;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(final Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + limit;
        result = prime * result + offset;
        result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
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
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
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
        return "PagedSearch [offset=" + offset + ", limit=" + limit + ", query=" + query + ", parameters=" + parameters + "]";
    }
}
