package org.optigra.ads.content.dao;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.version.VersionException;

import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.log4j.Logger;
import org.optigra.ads.content.exception.ContentException;
import org.optigra.ads.content.exception.ContentNotFoundException;
import org.optigra.ads.content.jcr.JcrSessionFactory;
import org.optigra.ads.content.model.Content;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository("jcrRepositoryImpl")
public class JcrRepositoryImpl implements ContentRepository {
	private static Logger logger = Logger.getLogger(JcrRepositoryImpl.class); 
	
	private static final String FILE = "file";
	private static final String CONTENT = "content";
	private static final String DATE = "date";

	
	@Resource(name = "jcrSessionFactory")
	private JcrSessionFactory sessionFactory;
	
	@Override
	public String storeContent(final Content content) throws ContentException {
		Session session = null;
		String id = null;
		try {
			session = sessionFactory.getCurrentSession();
			Assert.notNull(session);
					
			Node root = session.getRootNode();
			Node node = root.addNode(content.getPath());

			setNodeInformation(content, node);
			id = node.getIdentifier();
			
			logger.info(String.format("Content stored, id: %s", id));
			
			session.save();
		} catch (Throwable e) {
		    throw new ContentException();
		}
		
		return id;
	}

	
	private void setNodeInformation(final Content content, final Node node) throws ValueFormatException, VersionException, LockException, ConstraintViolationException, RepositoryException, IOException {
		Binary binary = new BinaryImpl(content.getStream());

		node.setProperty(FILE, binary);
		node.setProperty(CONTENT, content.getPayload());
		node.setProperty(DATE, content.getDate().getTime());
	}


	@Override
	public InputStream getContentByUuid(final String id) throws ContentNotFoundException {
		Assert.hasText(id, "ContentId cannot be null");
		Session session = null;
		InputStream stream = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			Node node = session.getNodeByIdentifier(id);
			Property prop = node.getProperty(FILE);
			stream = prop.getBinary().getStream();
			
		} catch (Throwable e) {
			throw new ContentNotFoundException();
		}
		
        return stream;
	}


    @Override
    public InputStream getContentByPath(final String path) throws ContentNotFoundException {
        Assert.hasText(path, "Path cannot be null");
        Session session = null;
        InputStream stream = null;
        
        try {
            session = sessionFactory.getCurrentSession();
            Node node = session.getNode(path);
            Property prop = node.getProperty(FILE);
            stream = prop.getBinary().getStream();
            
        } catch (Throwable e) {
            throw new ContentNotFoundException();
        }
        
        return stream;
    }
}
