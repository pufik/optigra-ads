package org.optigra.ads.rest.web.controller;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.facade.resource.application.ApplicationResource;
import org.optigra.ads.model.application.ApplicationStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest extends AbstractControllerTest {

    @Mock
    private ApplicationFacade facade;
    
    @InjectMocks
    private ApplicationController unit;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }
    
    @Test
    public void testAddAplication() throws Exception {
        writeFromFields(true);
        // Given
        String name = "application#2";
        String applicationId = "h5gfg543f5gh34fgh";
        ApplicationResource resource = new ApplicationResource();
        String url = "url";
        resource.setName(name);
        resource.setApplicationId(applicationId);
        resource.setStatus(ApplicationStatus.PENDING);
        resource.setUrl(url);
        MessageResource messageResource = new MessageResource(MessageType.INFO, "Application created");
        
        // Then
        mockMvc.perform(post(ResourceUri.APPLICATION)
                .content(objectMapper.writeValueAsString(resource))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(messageResource)));
        
        verify(facade).createApplication(resource);
        writeFromFields(false);
    }
    
    @Test
    public void testGetApplications() throws Exception {
        // Given
        long count = 100;
        int start = 0;
        int offset = 20;
        ApplicationResource applicationResource1 = new ApplicationResource();
        List<ApplicationResource> entities = Arrays.asList(applicationResource1 );
        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setCount(count);
        pagedResult.setOffset(offset);
        pagedResult.setStart(start);
        pagedResult.setEntities(entities);
        
        // When
        when(facade.getApplications(anyInt(), anyInt())).thenReturn(pagedResult);
        
        // Then
        mockMvc.perform(get(ResourceUri.APPLICATION))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(pagedResult)));
    }

    @Test
    public void testGetApplicationsWithParameters() throws Exception {
        // Given
        long count = 100;
        int start = 5;
        int offset = 34;
        ApplicationResource applicationResource1 = new ApplicationResource();
        List<ApplicationResource> entities = Arrays.asList(applicationResource1 );
        PagedResultResource<ApplicationResource> pagedResult = new PagedResultResource<>(ResourceUri.APPLICATION);
        pagedResult.setCount(count);
        pagedResult.setOffset(offset);
        pagedResult.setStart(start);
        pagedResult.setEntities(entities);
        
        // When
        when(facade.getApplications(anyInt(), anyInt())).thenReturn(pagedResult);
        
        // Then
        mockMvc.perform(get(ResourceUri.APPLICATION)
                .param("start", String.valueOf(start))
                .param("offset", String.valueOf(offset)))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(pagedResult)));
    }

    @Test
    public void testCheckApplicationStatus() throws Exception {
        // Given
        String applicationId = "ds4324kj23k5j23bn5";
        String status = ApplicationStatus.PENDING.name();
        MessageResource messageResource = new MessageResource(MessageType.INFO, status);        

        // When
        when(facade.getApplicationStatus(anyString())).thenReturn(status);
        
        // Then
        mockMvc.perform(get("/application/{appId}/status", applicationId))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(messageResource)));
    }
    
    @Test
    public void testGetApplication() throws Exception {
        // Given
        String applicationId = "appId"; 
        String name = "name";
        ApplicationResource applicationResource = new ApplicationResource();
        applicationResource.setApplicationId(applicationId);
        applicationResource.setName(name);
        applicationResource.setStatus(ApplicationStatus.PAID);
        
        // When
        when(facade.getApplication(anyString())).thenReturn(applicationResource);
        
        // Then
        mockMvc.perform(get("/application/{appId}", applicationId))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(applicationResource)));
    }
}
