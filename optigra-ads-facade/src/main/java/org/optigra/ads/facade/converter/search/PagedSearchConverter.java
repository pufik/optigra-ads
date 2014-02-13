package org.optigra.ads.facade.converter.search;

import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.Resource;
import org.springframework.stereotype.Component;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 * 
 */

@Component("pagedSearchConverter")
public class PagedSearchConverter extends AbstractConverter<PagedResult<?>, PagedResultResource<? extends Resource>> {

    @Override
    public PagedResultResource<? extends Resource> convert(final PagedResult<?> source) {
        throw new UnsupportedOperationException();
    }

    @Override
    public PagedResultResource<? extends Resource> convert(final PagedResult<?> source, final PagedResultResource<? extends Resource> target) {

        target.setStart(source.getStart());
        target.setOffset(source.getOffset());
        target.setCount(source.getCount());

        return target;
    }
}
