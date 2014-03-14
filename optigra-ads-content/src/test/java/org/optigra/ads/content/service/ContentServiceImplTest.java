package org.optigra.ads.content.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.content.dao.ContentRepository;
import org.optigra.ads.content.model.Content;

@RunWith(MockitoJUnitRunner.class)
public class ContentServiceImplTest {

    @Mock
    private ContentRepository contentRepository;
    
    @InjectMocks
    private ContentServiceImpl unit = new ContentServiceImpl();
    
    @Test
    public void testGetContentByUuid() throws Exception {
        // Given
        String id = "uuid";
        InputStream expectedStream = new ByteArrayInputStream("somstring".getBytes("UTF-8"));

        // When
        when(contentRepository.getContentByUuid(anyString())).thenReturn(expectedStream);
        InputStream actualStream = unit.getContentByUuid(id);

        // Then
        verify(contentRepository).getContentByUuid(id);
        assertEquals(expectedStream, actualStream);
    }

    @Test
    public void testGetContentByPath() throws Exception {
        // Given
        String path = "/path/to/file.ext";
        InputStream expectedStream = new ByteArrayInputStream("somstring".getBytes("UTF-8"));
        
        // When
        when(contentRepository.getContentByPath(anyString())).thenReturn(expectedStream);
        InputStream actualStream = unit.getContentByPath(path);
        
        // Then
        verify(contentRepository).getContentByPath(path);
        assertEquals(expectedStream, actualStream);
    }
    
    @Test
    public void testmethodName() throws Exception {
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
        
        String expectedContentId = "contentId";
        
        // When
        when(contentRepository.storeContent(any(Content.class))).thenReturn(expectedContentId);
        String actualContentId = unit.storeContent(content);

        // Then
        verify(contentRepository).storeContent(content);
        assertEquals(expectedContentId, actualContentId);
    }
}
