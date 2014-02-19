package org.optigra.ads.facade.converter.advertising;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.model.advertising.Advertising;

public class ResourceAdvertisingConverterTest {

    private ResourceAdvertisingConverter unit = new ResourceAdvertisingConverter();
    
    @Test
    public void testConvert() {
        // Given
        Long id = 1L;
        String description = "description";
        String destinationUrl = "descrination url";
        String imageUrl = "image url";
        String logoUrl = "logo url";
        String title = "title";
        
        AdvertisingResource source = new AdvertisingResource();
        source.setDescription(description);
        source.setDestinationUrl(destinationUrl);
        source.setImageUrl(imageUrl);
        source.setLogoUrl(logoUrl);
        source.setTitle(title);
        source.setUid(id);
        
        Advertising expected = new Advertising();
        expected.setDescription(description);
        expected.setDestinationUrl(destinationUrl);
        expected.setId(id);
        expected.setImageUrl(imageUrl);
        expected.setLogoUrl(logoUrl);
        expected.setTitle(title);
        
        // When
        Advertising actual = unit.convert(source);
        
        // Then
        assertEquals(expected, actual);
    }
}
