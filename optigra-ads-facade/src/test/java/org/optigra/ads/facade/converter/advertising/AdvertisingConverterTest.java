package org.optigra.ads.facade.converter.advertising;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.model.advertising.Advertising;

public class AdvertisingConverterTest {

    private AdvertisingConverter unit = new AdvertisingConverter();
    
    @Test
    public void testConvert() {
        // Given
        String description = "description";
        String destinationUrl = "destination url";
        String imageUrl = "image url";
        String logoUrl = "logo url";
        String title = "title";
        Long id = 1L;

        Advertising source = new Advertising();
        source.setDescription(description);
        source.setDestinationUrl(destinationUrl);
        source.setId(id);
        source.setImageUrl(imageUrl);
        source.setLogoUrl(logoUrl);
        source.setTitle(title);
        
        AdvertisingResource expected = new AdvertisingResource();
        expected.setDescription(description);
        expected.setDestinationUrl(destinationUrl);
        expected.setImageUrl(imageUrl);
        expected.setLogoUrl(logoUrl);
        expected.setTitle(title);
        expected.setUid(id);
        
        // When
        AdvertisingResource actual = unit.convert(source);
        
        // Then
        assertEquals(expected, actual);
    }
}
