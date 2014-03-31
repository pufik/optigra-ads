package org.optigra.ads.facade.content;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.content.model.Content;
import org.optigra.ads.content.model.ContentStrategy;
import org.optigra.ads.content.service.ContentService;
import org.optigra.ads.facade.content.resolver.ContentStrategyPathResolver;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.content.ContentResource;
import org.springframework.web.multipart.MultipartFile;

@RunWith(MockitoJUnitRunner.class)
public class DefaultContentFacadeTest {

    private String fileName = "filename.jpg";
    
    @Captor
    private ArgumentCaptor<Content> contentCaptor;
    
    @Mock
    private ContentService contentService;
    
    @Mock
    private ContentStrategyPathResolver strategyPathResolver; 

    @Mock
    private Converter<Content, ContentResource> contentConverter;
    
    @Mock
    private MultipartFile file;
    
    @InjectMocks
    private DefaultContentFacade unit = new DefaultContentFacade();
    
    @Before
    public void setup() {
        when(file.getOriginalFilename()).thenReturn(fileName);
    }
    
    @Test
    public void testGetContentByPath() throws Exception {
        // Given
        String contentPath = "/contentPath";
        String content = "very large content";
        InputStream expectedStream = new ByteArrayInputStream(content.getBytes());
        
        // When
        when(contentService.getContentByPath(anyString())).thenReturn(expectedStream);
        
        InputStream actualStream = unit.getContentByPath(contentPath);

        // Then
        verify(contentService).getContentByPath(contentPath);
        assertEquals(expectedStream, actualStream);
    }

    @Test
    public void testStoreContent() throws Exception {
        // Given
        String inputContent = "very big content";
        String fileName = "fileName";
        InputStream stream = new ByteArrayInputStream(inputContent.getBytes());
        ContentStrategy contentStrategy = ContentStrategy.USER;
        String path = "/users/%s/filename";

        ContentResource expectedContentResource = new ContentResource();
        expectedContentResource.setPath(path);
        
        Content content = new Content();
        content.setPath(path);
        content.setStream(stream);
        content.setFileName(fileName);
        
        // When
        when(strategyPathResolver.getPath(any(ContentStrategy.class))).thenReturn(path);
        when(contentConverter.convert(any(Content.class))).thenReturn(expectedContentResource);
        
        ContentResource actualContentResource = unit.storeContent(stream, fileName, contentStrategy);

        // Then
        verify(contentService).storeContent(contentCaptor.capture());
        assertEquals(content, contentCaptor.getValue());
        assertEquals(expectedContentResource, actualContentResource);
    }
}
