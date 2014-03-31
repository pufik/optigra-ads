package org.optigra.ads.content.service;

import java.io.InputStream;

import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.model.Content;

public interface ContentService {
    
	InputStream getContentByUuid(String id) throws ContentNotFoundException;

	InputStream getContentByPath(String path) throws ContentNotFoundException;
	
	String storeContent(Content content) throws ContentException;
	
}