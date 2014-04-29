package org.optigra.ads.rest.web.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.device.DeviceFacade;
import org.optigra.ads.facade.resource.device.DeviceResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class DeviceControllerTest extends AbstractControllerTest {

	@Mock
	private DeviceFacade deviceFacade;
	
    @InjectMocks
    private final DeviceController unit = new DeviceController();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
	public void testCreateDevice() throws Exception {
		// Given
    	String deviceToken = "device token";
    	String deviceUid = "device uid";
    	DeviceResource deviceResource = new DeviceResource();
    	deviceResource.setDeviceToken(deviceToken);
    	deviceResource.setDeviceUid(deviceUid);

		// When
		String request = getJson(deviceResource, true);

		// Then
    	mockMvc.perform(post("/device")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(request))
    		.andExpect(status().isOk());
    	
    	// Verify
    	verify(deviceFacade).createDevice(deviceResource);
	}
    
    @Test
	public void testUpdateDevice() throws Exception {
		// Given
    	String deviceToken = "device token";
    	String deviceUid = "device uid";
    	DeviceResource deviceResource = new DeviceResource();
    	deviceResource.setDeviceToken(deviceToken);
    	deviceResource.setDeviceUid(deviceUid);


		// When
		String request = getJson(deviceResource, true);
		
		// Then
    	mockMvc.perform(put("/device/{deviceUid}", deviceUid)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(request))
    		.andExpect(status().isOk());
    	verify(deviceFacade).updateDevice(deviceUid, deviceResource);
	}
    
    @Test
	public void testDeleteDevice() throws Exception {
		// Given
    	String deviceUid = "device uid";
    	
		// Then
    	mockMvc.perform(delete("/device/{deviceUid}", deviceUid))
    		.andExpect(status().isOk());
    	
    	verify(deviceFacade).deleteDevice(deviceUid);
	}
    
    @Test
	public void testGetDevice() throws Exception {
		// Given
    	String deviceUid = "device uid";
    	String deviceToken = "device Token";

    	DeviceResource deviceResource = new DeviceResource();
    	deviceResource.setDeviceUid(deviceUid);
		deviceResource.setDeviceToken(deviceToken);
    	
		// When
		when(deviceFacade.getDevice(anyString())).thenReturn(deviceResource);
    	String response = getJson(deviceResource, false);

		// Then
    	mockMvc.perform(get("/device/{deviceUid}", deviceUid))
    		.andExpect(status().isOk())
    		.andExpect(content().string(response));
    	verify(deviceFacade).getDevice(deviceUid);
	}
}
