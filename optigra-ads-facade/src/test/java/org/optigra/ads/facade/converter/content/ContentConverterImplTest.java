package org.optigra.ads.facade.converter.content;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;
import org.optigra.ads.content.model.Content;
import org.optigra.ads.facade.resource.content.ContentResource;

public class ContentConverterImplTest {

    private ContentConverterImpl unit = new ContentConverterImpl();
    
    @Test
    public void testConvert() throws Exception {
        // Given
        InputStream stream = new ByteArrayInputStream("veryLarge".getBytes());
        String contentId = "contentId";
        String fileName = "filename";
        String path = "path";
        
        Content source = new Content();
        source.setContentId(contentId);
        source.setFileName(fileName);
        source.setPath(path);
        source.setStream(stream);

        ContentResource expected = new ContentResource();
        expected.setContentId(contentId);
        expected.setFileName(fileName);
        expected.setPath(path);
        
        // When
        ContentResource actual = unit.convert(source);

        // Then
        assertEquals(expected, actual);
    }
}
