package org.optigra.ads.facade.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.facade.converter.Converter;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.service.application.ApplicationService;


@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationFacadeTest {

    @Captor
    private ArgumentCaptor<PagedResult<?>> pagedResultCaptor;
    
    @Captor
    private ArgumentCaptor<PagedResultResource<? extends Resource>> pagedResultResource;
    
    @Captor
    private ArgumentCaptor<Application> applicationCaptor;
    
    @Mock
    private Converter<Application, ApplicationResource> applicationConverter;
    
    @Mock
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;

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
    
    @Test
    public void testGetApplications() {
        // Given
        int start = 0;
        int offset = 25;
        long count = 100;
        String uri = ResourceUri.APPLICATION;
        Application applications1 = new Application();
        ApplicationResource applicationsResource1 = new ApplicationResource();
        List<Application> entities = Arrays.asList(applications1);
        List<ApplicationResource> applicationResources = Arrays.asList(applicationsResource1);
        PagedResult<Application> result = new PagedResult<Application>(start, offset, count, entities);
        PagedResultResource<ApplicationResource> expecteds = new PagedResultResource<>(uri);
        expecteds.setEntities(applicationResources);
        
        
        // When
        when(applicationService.getApplications(anyInt(), anyInt())).thenReturn(result);
        when(applicationConverter.convertAll(anyListOf(Application.class))).thenReturn(applicationResources);
        
        PagedResultResource<ApplicationResource> actuals = unit.getApplications(start, offset);
        
        // Then
        
        verify(pagedSearchConverter).convert(pagedResultCaptor.capture(), pagedResultResource.capture());
        
        assertEquals(result, pagedResultCaptor.getValue());
        assertEquals(expecteds, actuals);
    }
    
    @Test
    public void testGetApplicationStatus() {
        // Given
        String applicationId = "ds4324kj23k5j23bn5";
        String expected = null;
        
        // When
        when(applicationService.getApplicationStatus(anyString())).thenReturn(expected);
        
        String actual = unit.getApplicationStatus(applicationId);
        
        // Then
        verify(applicationService).getApplicationStatus(applicationId);
        
        assertEquals(expected, actual);
    }
    
}
