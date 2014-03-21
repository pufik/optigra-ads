package org.optigra.ads.facade.converter.application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;

/**
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
public class ApplicationResourceConverterTest {

    private ApplicationResourceConverter unit = new ApplicationResourceConverter();
    
    @Test
    public void testConvert() {
        // Given
        Long id = 1L;
        ApplicationStatus status = ApplicationStatus.PENDING;
        String url = "url";
        String name = "name";
        String applicationId = "5h34g6f34g6g45hf6gh45f6gh45";
        String groupId = "-53453434";
        String groupName = "groupName";
        String imageUrl = "imageUrl";
        
        Application expected = new Application();
        expected.setApplicationId(applicationId);
        expected.setName(name);
        expected.setStatus(status);
        expected.setUrl(url);
        expected.setGroupId(groupId);
        expected.setGroupName(groupName);
        expected.setImageUrl(imageUrl);
        
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setId(id);
        applicationResource.setApplicationId(applicationId);
        applicationResource.setName(name);
        applicationResource.setStatus(status);
        applicationResource.setUrl(url);
        applicationResource.setGroupId(groupId);
        applicationResource.setGroupName(groupName);
        applicationResource.setImageUrl(imageUrl);
        
        // When
        Application actual = unit.convert(applicationResource);
        
        // Then
        assertEquals(expected, actual);
    }
}
