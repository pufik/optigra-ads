package org.optigra.ads.rest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.advertising.AdvertisingFacade;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.MessageResource;
import org.optigra.ads.facade.resource.MessageType;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisingControllerTest extends AbstractControllerTest {

    @Captor
    private ArgumentCaptor<AdvertisingResource> advertisingCaptor;
    
    @Mock
    private AdvertisingFacade advertisingFacade;
    
    @InjectMocks
    private final AdvertisingController unit = new AdvertisingController();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetAdvertisings() throws Exception {
        // Given
        long count = 100;
        int start = 0;
        int offset = 20;
        AdvertisingResource entity1 = new AdvertisingResource();
        List<AdvertisingResource> entities = Arrays.asList(entity1);

        PagedResultResource<AdvertisingResource> expected = new PagedResultResource<>(ResourceUri.ADVERTISING);
        expected.setCount(count);
        expected.setEntities(entities);
        expected.setOffset(offset);
        expected.setStart(start );
        
        // When
        when(advertisingFacade.getAdvertisings(anyInt(), anyInt())).thenReturn(expected);
        
        // When
        mockMvc.perform(get("/advertising"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(expected)));
        
        verify(advertisingFacade).getAdvertisings(start, offset);
    }
    
    @Test
    public void testCreateAdvertising() throws Exception {
        // Given
        writeFromFields(true);
        AdvertisingResource advertisingResource = new AdvertisingResource();
        MessageResource messageResource = new MessageResource(MessageType.INFO, "Advertising created");
        
        // Then
        mockMvc.perform(post("/advertising")
                .content(objectMapper.writeValueAsString(advertisingResource))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(messageResource)));
        
        verify(advertisingFacade).createAdvertising(advertisingCaptor.capture());
        assertEquals(advertisingResource, advertisingCaptor.getValue());
    }
}
