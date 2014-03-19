package org.optigra.ads.facade.application;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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
import org.optigra.ads.model.application.ApplicationStatus;
import org.optigra.ads.service.application.ApplicationService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultApplicationFacadeTest {


    @Captor
    private ArgumentCaptor<String> stringCaptor;

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
    private Converter<ApplicationResource, Application> applicationResourceConverter;

    @Mock
    private ApplicationService applicationService;

    @InjectMocks
    private final ApplicationFacade unit = new DefaultApplicationFacade();

    @Test
    public void testCreateApplication() {
        // Given
        String name = "application";
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setName(name);
        Application application = new Application();
        application.setName(name);

        // When
        when(applicationResourceConverter.convert(any(ApplicationResource.class))).thenReturn(application);
        when(applicationConverter.convert(any(Application.class))).thenReturn(applicationResource);
        
        ApplicationResource actualResource = unit.createApplication(applicationResource);

        verify(applicationService).createApplication(applicationCaptor.capture());
        verify(applicationResourceConverter).convert(applicationResource);
        verify(applicationConverter).convert(application);
        
        // Then
        assertEquals(application, applicationCaptor.getValue());
        assertEquals(applicationResource, actualResource);
    }

    @Test
    public void testGetApplications() {
        // Given
        int offset = 0;
        int limit = 25;
        long count = 100;
        String uri = ResourceUri.APPLICATION;
        Application applications1 = new Application();
        ApplicationResource applicationsResource1 = new ApplicationResource();
        List<Application> entities = Arrays.asList(applications1);
        List<ApplicationResource> applicationResources = Arrays.asList(applicationsResource1);
        PagedResult<Application> result = new PagedResult<Application>(offset, limit, count, entities);
        PagedResultResource<ApplicationResource> expecteds = new PagedResultResource<>(uri);
        expecteds.setEntities(applicationResources);

        // When
        when(applicationService.getApplications(anyInt(), anyInt())).thenReturn(result);
        when(applicationConverter.convertAll(anyListOf(Application.class))).thenReturn(applicationResources);

        PagedResultResource<ApplicationResource> actuals = unit.getApplications(offset, limit);

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

    @Test
    public void testGetApplication() {
        // Given
        String applicationId = "appId";
        Long id = 1L;
        String name = "name";
        String url = "www.vk.com/ios-kakashka";

        ApplicationResource expected = new ApplicationResource();
        expected.setApplicationId(applicationId);
        expected.setId(id);
        expected.setName(name);
        expected.setStatus(ApplicationStatus.PENDING);
        expected.setUrl(url);

        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setId(id);
        application.setName(name);
        application.setStatus(ApplicationStatus.PENDING);
        application.setUrl(url);

        // When
        when(applicationService.getApplication(anyString())).thenReturn(application);
        when(applicationConverter.convert(any(Application.class))).thenReturn(expected);

        ApplicationResource actual = unit.getApplication(applicationId);

        // Then
        verify(applicationService).getApplication(applicationId);
        verify(applicationConverter).convert(application);

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteApplication() {
        // Given
        String applicationId = "gh5f24hg5f43g";

        // When
        unit.deleteApplication(applicationId);

        // Then
        verify(applicationService).deleteApplication(stringCaptor.capture());
        assertEquals(applicationId, stringCaptor.getValue());
    }
    
    @Test
    public void testUpdateApplication() throws Exception {
        // Given
        String applicationId = "applicationId";
        String url = "url";
        String groupId = "-534534534";
        String groupName = "groupName";
        String imageUrl = "imageUrl";
        String name = "name";

        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setApplicationId(applicationId);
        applicationResource.setGroupId(groupId);
        applicationResource.setGroupName(groupName);
        applicationResource.setImageUrl(imageUrl);
        applicationResource.setName(name);
        applicationResource.setUrl(url );

        Application application = new Application();
        application.setApplicationId(applicationId);
        application.setGroupId(groupId);
        application.setGroupName(groupName);
        application.setImageUrl(imageUrl);
        application.setName(name);
        application.setUrl(url );
        
        // When
        when(applicationService.getApplication(anyString())).thenReturn(application);
        
        unit.updateApplication(applicationId, applicationResource);

        // Then
        verify(applicationResourceConverter).convert(eq(applicationResource), eq(application));
        verify(applicationService).updateApplication(applicationCaptor.capture());
        assertEquals(application, applicationCaptor.getValue());
    }
}
