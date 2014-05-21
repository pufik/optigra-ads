package org.optigra.ads.content.service;

import java.io.InputStream;

import javax.annotation.Resource;

import org.optigra.ads.content.dao.ContentRepository;
import org.optigra.ads.content.model.Content;
import org.springframework.stereotype.Service;

@Service("contentService")
public class DefaultContentService implements ContentService {

	@Resource(name = "jcrRepositoryImpl")
	private ContentRepository contentRepository;

	@Override
	public InputStream getContentByUuid(final String id) {
		return contentRepository.getContentByUuid(id);
	}
	
	@Override
	public InputStream getContentByPath(final String path) {
	    return contentRepository.getContentByPath(path);
	}

	@Override
	public String storeContent(final Content content) {
		return contentRepository.storeContent(content);
	}

}
