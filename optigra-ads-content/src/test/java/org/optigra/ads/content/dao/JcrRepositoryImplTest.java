package org.optigra.ads.content.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.content.jcr.JcrSessionFactory;
import org.optigra.ads.content.model.Content;

@RunWith(MockitoJUnitRunner.class)
public class JcrRepositoryImplTest {

    private static final String CONTENT = "content";
    private static final String DATE = "date";
    
    @Mock
    private Session session;
    
    @Mock
    private JcrSessionFactory sessionFactory;
    
    @Mock
    private Node node;
    
    @Mock
    private Property property;
    
    @Mock
    private Binary binary;

    @InjectMocks
    private JcrRepositoryImpl unit = new JcrRepositoryImpl();
    
    @Test
    public void testGetContentByUuid() throws Exception {
        // Given
        String contentId = "contentId";
        InputStream expectedStream = new ByteArrayInputStream("somstring".getBytes("UTF-8"));

        // When
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getNodeByIdentifier(anyString())).thenReturn(node);
        when(node.getProperty(anyString())).thenReturn(property);
        when(property.getBinary()).thenReturn(binary);
        when(binary.getStream()).thenReturn(expectedStream);

        InputStream actualStream = unit.getContentByUuid(contentId);

        // Then
        verify(sessionFactory).getCurrentSession();
        verify(session).getNodeByIdentifier(contentId);
        verify(property).getBinary();
        verify(binary).getStream();
        
        assertEquals(expectedStream, actualStream);
    }

    @Test
    public void testGetContentByPath() throws Exception {
        // Given
        String contentId = "contentId";
        InputStream expectedStream = new ByteArrayInputStream("somstring".getBytes("UTF-8"));
        
        // When
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getNode(anyString())).thenReturn(node);
        when(node.getProperty(anyString())).thenReturn(property);
        when(property.getBinary()).thenReturn(binary);
        when(binary.getStream()).thenReturn(expectedStream);
        
        InputStream actualStream = unit.getContentByPath(contentId);
        
        // Then
        verify(sessionFactory).getCurrentSession();
        verify(session).getNode(contentId);
        verify(property).getBinary();
        verify(binary).getStream();
        
        assertEquals(expectedStream, actualStream);
    }
    
    @Test
    public void testStoreContent() throws Exception {
        // Given
        String payload = "payload";
        InputStream stream = new ByteArrayInputStream("somstring".getBytes("UTF-8"));
        String path = "/path/to/my/file.ext";
        Date date = Calendar.getInstance().getTime();
        
        Content content = new Content();
        content.setPayload(payload);
        content.setDate(date);
        content.setStream(stream);
        content.setPath(path);
        
        // When
        String expectedContentId = "contentId";

        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getRootNode()).thenReturn(node);
        when(node.addNode(anyString())).thenReturn(node);
        when(node.getIdentifier()).thenReturn(expectedContentId);
        
        String actualContentId = unit.storeContent(content);

        // Then
        verify(sessionFactory).getCurrentSession();
        verify(session).getRootNode();
        verify(node).addNode(path);
        verify(node).getIdentifier();
        verify(session).save();
        
        verify(node).setProperty(CONTENT, payload);
        verify(node).setProperty(DATE, date.getTime());
        
        assertEquals(expectedContentId, actualContentId);
    }
}
