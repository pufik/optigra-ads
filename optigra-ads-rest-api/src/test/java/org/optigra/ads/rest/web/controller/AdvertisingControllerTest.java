package org.optigra.ads.rest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
        int offset = 0;
        int limit = 20;
        AdvertisingResource entity1 = new AdvertisingResource();
        List<AdvertisingResource> entities = Arrays.asList(entity1);

        PagedResultResource<AdvertisingResource> expected = new PagedResultResource<>(ResourceUri.ADVERTISING);
        expected.setCount(count);
        expected.setEntities(entities);
        expected.setOffset(offset);
        expected.setLimit(limit);

        // When
        when(advertisingFacade.getAdvertisings(anyInt(), anyInt())).thenReturn(expected);

        // When
        mockMvc.perform(get("/advertising"))
            .andExpect(status().isOk())
            .andExpect(content().string(objectMapper.writeValueAsString(expected)));

        verify(advertisingFacade).getAdvertisings(offset, limit);
    }
    
    @Test
    public void testCreateAdvertising() throws Exception {
        // Given
        String description = "description";
        AdvertisingResource advertisingResource = new AdvertisingResource();
		advertisingResource.setDescription(description);
        
        // Then
        when(advertisingFacade.createAdvertising(advertisingResource)).thenReturn(advertisingResource);
        
        String request = getJson(advertisingResource, true);
        String response = getJson(advertisingResource, false);
        
        mockMvc.perform(post("/advertising")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string(response));
        
        verify(advertisingFacade).createAdvertising(advertisingCaptor.capture());
        assertEquals(advertisingResource, advertisingCaptor.getValue());
    }
    
    @Test
	public void testUpdateAdvertising() throws Exception {
		// Given
    	Long advertisingId = 3L;
    	String description = "description";
    	String destinationUrl = "desctionation Url";
    	String imageUrl = "image url";
    	String logoUrl = "logo url";
    	String title = "title";
    	
    	MessageResource messageResource = new MessageResource(MessageType.INFO, "Advertising updated");
    	
    	AdvertisingResource advertisingResource = new AdvertisingResource();
		advertisingResource.setDescription(description);
		advertisingResource.setDestinationUrl(destinationUrl);
		advertisingResource.setImageUrl(imageUrl);
		advertisingResource.setLogoUrl(logoUrl);
		advertisingResource.setTitle(title);
		advertisingResource.setUid(advertisingId);
		
        String request = getJson(advertisingResource, true);
        String response = getJson(messageResource, false);
		
    	// Then
		mockMvc.perform(put("/advertising/{advertisingId}", advertisingId)
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(response));
		verify(advertisingFacade).updateAdvertising(advertisingId, advertisingResource);
	}
    
    @Test
	public void testGetAdvertising() throws Exception {
		// Given
    	Long advertisingId = 3L;
    	String description = "description";
    	String destinationUrl = "desctionation Url";
    	String imageUrl = "image url";
    	String logoUrl = "logo url";
    	String title = "title";
    	
    	AdvertisingResource advertisingResource = new AdvertisingResource();
		advertisingResource.setDescription(description);
		advertisingResource.setDestinationUrl(destinationUrl);
		advertisingResource.setImageUrl(imageUrl);
		advertisingResource.setLogoUrl(logoUrl);
		advertisingResource.setTitle(title);
		advertisingResource.setUid(advertisingId);
    	
		// When
		when(advertisingFacade.getAdvertising(anyLong())).thenReturn(advertisingResource);
    	String response = getJson(advertisingResource, false);
    	
		// Then
    	mockMvc.perform(get("/advertising/{advertisingId}", advertisingId))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
    	verify(advertisingFacade).getAdvertising(advertisingId);
	}
    
    @Test
	public void testDeleteAdvertising() throws Exception {
		// Given
    	Long advertisingId = 3L;
    	
    	MessageResource messageResource = new MessageResource(MessageType.INFO, "Advertising deleted");
    	String response = getJson(messageResource, false);
    	
		// Then
    	mockMvc.perform(delete("/advertising/{advertisingId}", advertisingId))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
    	verify(advertisingFacade).deleteAdvertising(advertisingId);
	}
}
