package org.optigra.ads.service.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.model.application.Application;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationServiceTest {

    @Captor
    private ArgumentCaptor<Application> applicationCaptor;
    
    @Mock
    private ApplicationDao applicationDao;
    
    @InjectMocks
    private DefaultApplicationService unit = new DefaultApplicationService();
    
    @Test
    public void testCreateApplication() {
        // Given
        Long userId = 1L;
        Application application = new Application();
        application.setId(userId);
        
        // When
        unit.createApplication(application);
        
        verify(applicationDao).createApplication(applicationCaptor.capture());
        
        // Then
        assertEquals(application, applicationCaptor.getValue());
    }
}
