package org.optigra.ads.rest.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.facade.resource.ApiDetailsResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class SystemControllerTest extends AbstractControllerTest {

    private final SystemController unit = new SystemController();

    private MockMvc mockMvc;

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
