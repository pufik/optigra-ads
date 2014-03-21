package org.optigra.ads.facade.converter.content;

import org.optigra.ads.content.model.Content;
import org.optigra.ads.facade.converter.AbstractConverter;
import org.optigra.ads.facade.resource.content.ContentResource;
import org.springframework.stereotype.Component;

@Component("contentConverter")
public class ContentConverterImpl extends AbstractConverter<Content, ContentResource> {

    @Override
    public ContentResource convert(final Content source, final ContentResource target) {
        
        target.setContentId(source.getContentId());
        target.setFileName(source.getFileName());
        target.setPath(source.getPath());
        
        return target;
    }

    @Override
    public ContentResource convert(final Content source) {
        return convert(source, new ContentResource());
    }

}
