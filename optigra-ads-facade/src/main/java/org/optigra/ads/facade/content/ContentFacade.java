package org.optigra.ads.facade.content;

import java.io.InputStream;

import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.model.ContentStrategy;
import org.optigra.ads.facade.resource.content.ContentResource;

public interface ContentFacade {

    InputStream getContentByPath(String contentPath) throws ContentNotFoundException;

    ContentResource storeContent(InputStream stream, String fileName, ContentStrategy contentStrategy) throws ContentException ;
}