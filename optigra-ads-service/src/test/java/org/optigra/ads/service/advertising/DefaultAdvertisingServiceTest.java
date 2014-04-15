package org.optigra.ads.service.advertising;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
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
import org.optigra.ads.dao.advertising.AdvertisingDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.advertising.Advertising;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAdvertisingServiceTest {

    @Captor
    private ArgumentCaptor<Advertising> advertisingCaptor;
    
    @Mock
    private AdvertisingDao advertisingDao;
    
    @InjectMocks
    private DefaultAdvertisingService unit = new DefaultAdvertisingService();
    
    @Test
    public void testGetAdvertisings() {
        // Given
        int start = 0;
        int offset = 20;
        long count= 100;
        Advertising entity1 = new Advertising();
        List<Advertising> entities = Arrays.asList(entity1);
        PagedResult<Advertising> expected = new PagedResult<Advertising>(start, offset, count, entities);
        
        // When
        when(advertisingDao.getAdvertisings(anyInt(), anyInt())).thenReturn(expected);
        
        PagedResult<Advertising> actual = unit.getAdvertisings(start, offset);
        
        // Then
        assertEquals(expected, actual);
    }
    
    @Test
    public void testAddAdvertising() {
        // Given
        Advertising advertising = new Advertising();
        
        // When
        unit.createAdvertising(advertising);
        
        // Then
        verify(advertisingDao).create(advertisingCaptor.capture());
        assertEquals(advertising, advertisingCaptor.getValue());
    }
    
    @Test
	public void testGetAdvertising() throws Exception {
		// Given
    	Long advertisingId = 1L;
    	
    	Advertising expected = new Advertising();

		// When
    	when(advertisingDao.findById(anyLong())).thenReturn(expected);
    	
    	Advertising actual = unit.getAdvertising(advertisingId);

		// Then
    	verify(advertisingDao).findById(advertisingId);
    	assertEquals(expected, actual);
	}
    
    @Test
	public void testUpdateAdvertising() throws Exception {
		// Given
    	Long id = 2L;
    	Advertising advertising = new Advertising();
		advertising.setId(id);

		// When
    	unit.updateAdvertising(advertising);

		// Then
    	verify(advertisingDao).update(advertising);
	}
    
    @Test
	public void testDeleteAdvertising() throws Exception {
		// Given
    	Long advertisingId = 1L;

		// When
    	unit.deleteAdvertising(advertisingId);

		// Then
    	verify(advertisingDao).removeById(advertisingId);
	}
}

