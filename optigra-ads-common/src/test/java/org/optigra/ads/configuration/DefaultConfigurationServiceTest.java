package org.optigra.ads.configuration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DefaultConfigurationServiceTest {

    @Mock
    private Properties applicationProperties;

    @InjectMocks
    private final DefaultConfigurationService unit = new DefaultConfigurationService();

    @Test
    public void testGetProperty() {
        // Given
        String key = "key";
        String expectedValue = "value";
        when(applicationProperties.getProperty(eq(key))).thenReturn(expectedValue);

        // When
        String actualValue = unit.getProperty(key);

        // Then
        assertEquals(expectedValue, actualValue);
        verify(applicationProperties).getProperty(eq(key));

    }
}
