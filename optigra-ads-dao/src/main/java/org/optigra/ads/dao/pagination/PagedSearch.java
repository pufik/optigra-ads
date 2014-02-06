package org.optigra.ads.dao.pagination;

import java.util.Map;

/**
 * Class for paged queries.
 * @date Feb 6, 2014
 * @author ivanursul
 *
 */
public class PagedSearch {
    
    private int start;
    private int offset;
    private String namedQuery;
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
    
    public String getNamedQuery() {
        return namedQuery;
    }
    public void setNamedQuery(final String namedQuery) {
        this.namedQuery = namedQuery;
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
        result = prime * result
                + ((namedQuery == null) ? 0 : namedQuery.hashCode());
        result = prime * result + offset;
        result = prime * result
                + ((parameters == null) ? 0 : parameters.hashCode());
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
        if (namedQuery == null) {
            if (other.namedQuery != null)
                return false;
        } else if (!namedQuery.equals(other.namedQuery))
            return false;
        if (offset != other.offset)
            return false;
        if (parameters == null) {
            if (other.parameters != null)
                return false;
        } else if (!parameters.equals(other.parameters))
            return false;
        if (start != other.start)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "PagedSearch [start=" + start + ", offset=" + offset
                + ", namedQuery=" + namedQuery + ", parameters=" + parameters
                + "]";
    }
    
}
