package org.optigra.ads.content.dao;

import java.io.InputStream;

import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.model.Content;

public interface ContentRepository {
    
	String storeContent(Content content) throws ContentException;
	
	InputStream getContentByUuid(String id) throws ContentNotFoundException;

	InputStream getContentByPath(String id) throws ContentNotFoundException;
	
}
