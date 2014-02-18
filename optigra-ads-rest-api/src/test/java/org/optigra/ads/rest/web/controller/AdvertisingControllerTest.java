package org.optigra.ads.rest.web.controller;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.optigra.ads.facade.advertising.AdvertisingFacade;
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class AdvertisingControllerTest extends AbstractControllerTest {

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
    public void testSearch() throws Exception {
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
}
