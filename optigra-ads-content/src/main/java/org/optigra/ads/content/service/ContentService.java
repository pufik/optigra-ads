package org.optigra.ads.content.service;

import java.io.InputStream;

import org.optigra.ads.content.model.Content;

public interface ContentService {
    
	InputStream getContentByUuid(String id);

	InputStream getContentByPath(String path);
	
	String storeContent(Content content);
	
}