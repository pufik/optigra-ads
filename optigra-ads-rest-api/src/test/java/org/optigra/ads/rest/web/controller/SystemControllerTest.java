package org.optigra.ads.rest.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.dto.ApiDetailsResource;
import org.optigra.ads.facade.dto.ResourceUri;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class SystemControllerTest {

    private final SystemController unit = new SystemController();

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetApiDetails() throws Exception {
        ApiDetailsResource apiDetails = new ApiDetailsResource();

        // When
        mockMvc.perform(get(ResourceUri.SLASH)).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(apiDetails)));
    }
}
