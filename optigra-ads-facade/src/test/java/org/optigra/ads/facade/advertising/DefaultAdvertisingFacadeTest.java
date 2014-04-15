package org.optigra.ads.facade.advertising;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
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
import org.optigra.ads.model.user.User;
import org.optigra.ads.security.session.Session;
import org.optigra.ads.security.session.SessionService;
import org.optigra.ads.service.advertising.AdvertisingService;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAdvertisingFacadeTest {

    @Captor
    private ArgumentCaptor<PagedResult<Advertising>> pagedResultCaptor;
    
    @Captor
    private ArgumentCaptor<PagedResultResource<AdvertisingResource>> pagedResultResourceCaptor;
    
    @Captor
    private ArgumentCaptor<Advertising> advertisingCaptor;
    
    @Mock
    private AdvertisingService advertisingService;
    
    @Mock
    private Converter<Advertising, AdvertisingResource> advertisingConverter;
    
    @Mock
    private Converter<AdvertisingResource, Advertising> resourceAdvertisingConverter;
    
    @Mock
    private Converter<PagedResult<?>, PagedResultResource<? extends org.optigra.ads.facade.resource.Resource>> pagedSearchConverter;
    
    @Mock
    private SessionService sessionService;
    
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
    
    @Test
    public void testCreateAdvertising() {
        // Given
        Long id = 1L;
        String description = "description";
        String destinationUrl = "destination url";
        String imageUrl = "image url";
        String logoUrl = "logo url";
        String title = "title";
        
        AdvertisingResource expected = new AdvertisingResource();
        expected.setDescription(description);
        expected.setDestinationUrl(destinationUrl);
        expected.setImageUrl(imageUrl);
        expected.setLogoUrl(logoUrl);
        expected.setTitle(title);
        expected.setUid(id);
        
        Advertising advertising = new Advertising();
        advertising.setDescription(description);
        advertising.setDestinationUrl(destinationUrl);
        advertising.setId(id);
        advertising.setImageUrl(imageUrl);
        advertising.setLogoUrl(logoUrl);
        advertising.setTitle(title);
        
        User user = new User();
		Session session = new Session(user);
		
		// When
        when(sessionService.getCurrentSession()).thenReturn(session);
        when(resourceAdvertisingConverter.convert(any(AdvertisingResource.class))).thenReturn(advertising);
        when(advertisingConverter.convert(any(Advertising.class))).thenReturn(expected);
        
        AdvertisingResource actual = unit.createAdvertising(expected);
        
        // Then
        verify(advertisingService).createAdvertising(advertisingCaptor.capture());
        assertEquals(advertising, advertisingCaptor.getValue());
        assertEquals(expected, actual);
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
    	
    	AdvertisingResource advertisingResource = new AdvertisingResource();
		advertisingResource.setDescription(description);
		advertisingResource.setDestinationUrl(destinationUrl);
		advertisingResource.setImageUrl(imageUrl);
		advertisingResource.setLogoUrl(logoUrl);
		advertisingResource.setTitle(title);
		advertisingResource.setUid(advertisingId);

		Advertising advertising = new Advertising();
		advertising.setDescription(description);
		advertising.setDestinationUrl(destinationUrl);
		advertising.setImageUrl(imageUrl);
		advertising.setLogoUrl(logoUrl);
		advertising.setTitle(title);
		
		// When
		when(advertisingService.getAdvertising(anyLong())).thenReturn(advertising);
		
    	unit.updateAdvertising(advertisingId, advertisingResource);

		// Then
    	verify(resourceAdvertisingConverter).convert(advertisingResource, advertising);
    	verify(advertisingService).updateAdvertising(advertising);
	}
    
    @Test
	public void testGetAdvertising() throws Exception {
		// Given
    	Long advertisingId = 1L;
    	String description = "description";
    	String destinationUrl = "desctionation Url";
    	String imageUrl = "image url";
    	String logoUrl = "logo url";
    	String title = "title";
    	
    	AdvertisingResource expected = new AdvertisingResource();
		expected.setDescription(description);
		expected.setDestinationUrl(destinationUrl);
		expected.setImageUrl(imageUrl);
		expected.setLogoUrl(logoUrl);
		expected.setTitle(title);
		expected.setUid(advertisingId);
    	
		Advertising advertising = new Advertising();
		advertising.setDescription(description);
		advertising.setDestinationUrl(destinationUrl);
		advertising.setImageUrl(imageUrl);
		advertising.setLogoUrl(logoUrl);
		advertising.setTitle(title);
    	
		// When
    	when(advertisingService.getAdvertising(anyLong())).thenReturn(advertising);
    	when(advertisingConverter.convert(any(Advertising.class))).thenReturn(expected);
    	AdvertisingResource actual = unit.getAdvertising(advertisingId );

		// Then
    	assertEquals(expected, actual);
	}
    
    @Test
	public void testDeleteAdvertising() throws Exception {
		// Given
    	Long advertisingId = 1L;

		// When
    	unit.deleteAdvertising(advertisingId);

		// Then
    	verify(advertisingService).deleteAdvertising(advertisingId);
	}
}
