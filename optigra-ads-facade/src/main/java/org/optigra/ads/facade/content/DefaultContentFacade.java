package org.optigra.ads.facade.content;

import java.io.InputStream;

import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.model.Content;
import org.optigra.ads.content.model.ContentStrategy;
import org.optigra.ads.content.service.ContentService;
import org.optigra.ads.facade.content.resolver.ContentStrategyPathResolver;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.content.ContentResource;
import org.springframework.stereotype.Component;

@Component("contentFacade")
public class DefaultContentFacade implements ContentFacade {

	//@Resource(name = "contentService")
	private ContentService contentService;
	
	//@Resource(name = "contentStrategyPathResolver")
	private ContentStrategyPathResolver strategyPathResolver; 
	
	//@Resource(name = "contentConverter")
	private Converter<Content, ContentResource> contentConverter;
	
	@Override
	public InputStream getContentByPath(final String contentPath) throws ContentNotFoundException {
	    return contentService.getContentByPath(contentPath);
	}

    @Override
    public ContentResource storeContent(final InputStream stream, final String fileName, final ContentStrategy contentStrategy) throws ContentException {
        
        // Retrieve the required path, that will be saved in
        String path = strategyPathResolver.getPath(contentStrategy);
        
        // Initialize content object
        Content content = new Content();
        content.setPath(path);
        content.setStream(stream);
        content.setFileName(fileName);
        
        // Delegate work to service layer
        contentService.storeContent(content);
        
        // Object convertation due to some unnessesary field in Content model
        ContentResource contentResource = contentConverter.convert(content);
        
        return contentResource;
    }

}
