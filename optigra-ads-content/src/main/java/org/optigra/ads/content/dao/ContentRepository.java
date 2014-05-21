package org.optigra.ads.content.dao;

import java.io.InputStream;

import org.optigra.ads.content.model.Content;

public interface ContentRepository {
    
	String storeContent(Content content);
	
	InputStream getContentByUuid(String id);

	InputStream getContentByPath(String id);
	
}
