package org.optigra.ads.notification.reader;

import org.optigra.ads.model.pagination.BaseSearch;
import org.optigra.ads.model.pagination.PagedResult;

/**
 * 
 * @author ivanursul
 * 
 * @param <D> Data object, that will
 * @param <R>
 */
public interface ItemReader<D, R> {
	
	/**
	 * This method is for retrieving pagination results;
	 * 
	 * @param data
	 * @param search
	 * @return
	 */
	PagedResult<R> getItems(D data, BaseSearch search);
	
}
