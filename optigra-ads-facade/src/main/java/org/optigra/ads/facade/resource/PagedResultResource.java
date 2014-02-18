package org.optigra.ads.facade.resource;

import java.util.List;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 */
public class PagedResultResource<R extends Resource> extends Resource {

    private int offset;
    private int limit;
    private long count;
    private List<R> entities;

    private final String uri;

    public PagedResultResource(final String uri) {
        this.uri = uri;
    }

    @Override
    public String getUri() {
        return uri;
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

    public List<R> getEntities() {
        return entities;
    }

    public void setEntities(final List<R> entities) {
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
        result = prime * result + ((uri == null) ? 0 : uri.hashCode());
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
        PagedResultResource other = (PagedResultResource) obj;
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
        if (uri == null) {
            if (other.uri != null)
                return false;
        } else if (!uri.equals(other.uri))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PagedResultResource [offset=" + offset + ", limit=" + limit + ", count=" + count + ", entities=" + entities + ", uri=" + uri + "]";
    }
}
