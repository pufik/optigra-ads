package org.optigra.ads.facade.content.resolver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.content.model.ContentStrategy;

public class DefaultContentStrategyPathResolverTest {

    private DefaultContentStrategyPathResolver unit = new DefaultContentStrategyPathResolver();
    
    @Test
    public void testGetPath() throws Exception {
        // Given
        ContentStrategy contentStrategy = ContentStrategy.USER;

        String expectedPath = "user";
        
        // When
        String actualPath = unit.getPath(contentStrategy);

        // Then
        assertEquals(expectedPath, actualPath);
    }
    
}
