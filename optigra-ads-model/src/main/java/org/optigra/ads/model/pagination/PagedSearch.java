package org.optigra.ads.model.pagination;

import org.optigra.ads.model.query.Query;

/**
 * Class for paged queries.
 *
 * @date Feb 6, 2014
 * @author ivanursul
 */
public class PagedSearch<T> extends BaseSearch {

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
    	super(offset, limit);
        this.query = query;
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
		int result = super.hashCode();
		result = prime * result + ((query == null) ? 0 : query.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagedSearch<?> other = (PagedSearch<?>) obj;
		if (query == null) {
			if (other.query != null)
				return false;
		} else if (!query.equals(other.query))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PagedSearch [query=" + query + "]";
	}

}
