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
public class ApplicationConverterTest {

    private ApplicationConverter unit = new ApplicationConverter();
    
    @Test
    public void testConvertEntity() {
        // Given
        Long id = 1L;
        ApplicationStatus status = ApplicationStatus.PENDING;
        String url = "url";
        String name = "name";
        String applicationId = "5h34g6f34g6g45hf6gh45f6gh45";
        
        Application application = new Application();
        application.setId(id);
        application.setApplicationId(applicationId);
        application.setName(name);
        application.setStatus(status);
        application.setUrl(url);
        
        ApplicationResource expected = new ApplicationResource();
        expected.setId(id);
        expected.setApplicationId(applicationId);
        expected.setName(name);
        expected.setStatus(status);
        expected.setUrl(url);
        
        // When
        ApplicationResource actual = unit.convert(application);
        
        // Then
        assertEquals(expected, actual);
    }
    
}
