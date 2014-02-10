package org.optigra.ads.dao.pagination;

import java.util.Map;

import org.optigra.ads.common.Queries;

/**
 * Class for paged queries.
 * @date Feb 6, 2014
 * @author ivanursul
 *
 */
public class PagedSearch {
    
    private int start;
    private int offset;
    private Queries query;
    private Map<String, Object> parameters;
    
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
        result = prime * result + offset;
        result = prime * result
                + ((parameters == null) ? 0 : parameters.hashCode());
        result = prime * result + ((query == null) ? 0 : query.hashCode());
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
        PagedSearch other = (PagedSearch) obj;
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
        if (start != other.start)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagedSearch [start=" + start + ", offset=" + offset
                + ", query=" + query + ", parameters=" + parameters + "]";
    }
    
}
