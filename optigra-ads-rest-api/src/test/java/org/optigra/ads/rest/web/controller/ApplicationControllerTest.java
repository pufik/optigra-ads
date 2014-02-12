package org.optigra.ads.rest.web.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.application.ApplicationFacade;
import org.optigra.ads.facade.dto.MessageResource;
import org.optigra.ads.facade.dto.MessageType;
import org.optigra.ads.facade.dto.ResourceUri;
import org.optigra.ads.facade.dto.application.ApplicationResource;
import org.optigra.ads.model.application.ApplicationStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

    @Mock
    private ApplicationFacade facade;
    
    @InjectMocks
    private ApplicationController unit;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = getMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }
    
    private ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES); 
        
        objectMapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        
        return objectMapper;
    }

    @Test
    public void testAddAplication() throws Exception {
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
    }
    
    @Test
    public void testGetApplications() throws Exception {
        // Given
        
        // When
        
        // Then
        mockMvc.perform(get(ResourceUri.APPLICATION))
            .andExpect(status().isOk());
    }
}
