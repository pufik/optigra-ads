package org.optigra.ads.content.service;

import java.io.InputStream;

import javax.annotation.Resource;

import org.optigra.ads.content.dao.ContentRepository;
import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.model.Content;
import org.springframework.stereotype.Service;

@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Resource(name = "jcrRepositoryImpl")
	private ContentRepository contentRepository;

	@Override
	public InputStream getContentByUuid(final String id) throws ContentNotFoundException {
		return contentRepository.getContentByUuid(id);
	}
	
	@Override
	public InputStream getContentByPath(final String path) throws ContentNotFoundException {
	    return contentRepository.getContentByPath(path);
	}

	@Override
	public String storeContent(final Content content) throws ContentException {
		return contentRepository.storeContent(content);
	}

}
