package org.optigra.ads.rest.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AdvertisingControllerTest extends AbstractControllerTest {

    private final AdvertisingController unit = new AdvertisingController();

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testSearch() throws Exception {
        // Given
        AdvertisingResource resource = new AdvertisingResource();

        resource.setUid(BigDecimal.ZERO.longValue());
        resource.setTitle("Simple advertising example");
        resource.setDescription("Advertising description");
        resource.setDestinationUrl("http://company.com");
        resource.setImageUrl("/api/content/advertising/" + resource.getUid());
        resource.setLogoUrl("/api/content/advertising/logo/" + resource.getUid());

        List<AdvertisingResource> resources = Arrays.asList(resource);

        // When
        mockMvc.perform(get(ResourceUri.ADVERTISING)).andExpect(status().isOk()).andExpect(content().string(objectMapper.writeValueAsString(resources)));
    }
}
