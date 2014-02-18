package org.optigra.ads.facade.advertising;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
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
import org.optigra.ads.facade.resource.AdvertisingResource;
import org.optigra.ads.facade.resource.PagedResultResource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.advertising.Advertising;
import org.optigra.ads.service.advertising.AdvertisingService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAdvertisingFacadeTest {

    @Captor
    private ArgumentCaptor<PagedResult<Advertising>> pagedResultCaptor;
    
    @Captor
    private ArgumentCaptor<PagedResultResource<AdvertisingResource>> pagedResultResourceCaptor;
    
    @Mock
    private AdvertisingService advertisingService;
    
    @Mock
    private Converter<Advertising, AdvertisingResource> advertisingConverter;
    
    @Mock
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;
    
    @InjectMocks
    private DefaultAdvertisingFacade unit = new DefaultAdvertisingFacade();
    
    @Test
    public void testGetAdvertisings() {
        // Given
        int start = 0;
        int offset = 20;
        long count = 100;
        Advertising entity1 = new Advertising();
        List<Advertising> entities = Arrays.asList(entity1);
        PagedResult<Advertising> pagedResult = new PagedResult<Advertising>(start, offset, count, entities);
        AdvertisingResource resource1 = new AdvertisingResource();
        List<AdvertisingResource> resources = Arrays.asList(resource1);
        PagedResultResource<AdvertisingResource> expected = new PagedResultResource<>(ResourceUri.ADVERTISING);
        expected.setEntities(resources);
        
        // When
        when(advertisingService.getAdvertisings(anyInt(), anyInt())).thenReturn(pagedResult);
        when(advertisingConverter.convertAll(anyListOf(Advertising.class))).thenReturn(resources);
        
        PagedResultResource<AdvertisingResource> actual = unit.getAdvertisings(start, offset);
        
        // Then
        verify(advertisingService).getAdvertisings(start, offset);
        verify(advertisingConverter).convertAll(entities);
        verify(pagedSearchConverter).convert(pagedResultCaptor.capture(), pagedResultResourceCaptor.capture());

        assertEquals(expected, pagedResultResourceCaptor.getValue());
        assertEquals(expected, actual);
    }
}
