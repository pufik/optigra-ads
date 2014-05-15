package org.optigra.ads.model.pagination;

public class BaseSearch {

	protected int offset;
	protected int limit;

	public BaseSearch() {
		super();
	}

	public BaseSearch(int offset, int limit) {
		super();
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
	    return offset;
	}

	public int getLimit() {
	    return limit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + limit;
		result = prime * result + offset;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseSearch other = (BaseSearch) obj;
		if (limit != other.limit)
			return false;
		if (offset != other.offset)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BaseSearch [offset=" + offset + ", limit=" + limit + "]";
	}

}