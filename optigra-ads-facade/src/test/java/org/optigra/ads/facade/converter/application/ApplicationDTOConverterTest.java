package org.optigra.ads.facade.converter.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.dto.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
public class ApplicationDTOConverterTest {

    private ApplicationDTOConverter unit = new ApplicationDTOConverter();
    
    @Test
    public void testConvert() {
        // Given
        Long id = 1L;
        ApplicationStatus status = ApplicationStatus.PENDING;
        String url = "url";
        String name = "name";
        String applicationId = "5h34g6f34g6g45hf6gh45f6gh45";
        
        Application expected = new Application();
        expected.setId(id);
        expected.setApplicationId(applicationId);
        expected.setName(name);
        expected.setStatus(status);
        expected.setUrl(url);
        
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setId(id);
        applicationResource.setApplicationId(applicationId);
        applicationResource.setName(name);
        applicationResource.setStatus(status);
        applicationResource.setUrl(url);
        
        // When
        Application actual = unit.convert(applicationResource);
        
        // Then
        assertEquals(expected, actual);
    }
}
