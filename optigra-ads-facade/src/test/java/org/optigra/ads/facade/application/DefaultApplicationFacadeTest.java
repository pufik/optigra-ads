package org.optigra.ads.facade.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.dto.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.service.application.ApplicationService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationFacadeTest {

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;
    
    @Mock
    private Converter<ApplicationResource, Application> applicationDTOConverter;
    
    @Mock
    private ApplicationService applicationService;
    
    @InjectMocks
    private ApplicationFacade unit = new DefaultApplicationFacade();
    
    @Test
    public void testCreateApplication() {
        // Given 
        String name = "application";
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setName(name);
        Application application = new Application();
        application.setName(name);
        
        // When
        when(applicationDTOConverter.convert(any(ApplicationResource.class))).thenReturn(application);
        
        unit.createApplication(applicationResource);
        
        verify(applicationService).createApplication(applicationCaptor.capture());
        
        // Then
        assertEquals(application, applicationCaptor.getValue());
    }
    
}
