package org.optigra.ads.dao.advertising;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.common.Queries;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.advertising.Advertising;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAdvertisingDaoTest {
    
    private static final String TABLE_TOKEN = "$table";
    private static final String COUNT_QUERY = "SELECT COUNT(*) FROM " + TABLE_TOKEN + " a WHERE a IN(%s) ";
    
    @Mock
    private EntityManager entityManager;
    
    @Mock
    private TypedQuery<Advertising> typedQuery;
    
    @Mock
    private TypedQuery<Long> longTypedQuery;
    
    @InjectMocks
    private DefaultAdvertisingDao unit = new DefaultAdvertisingDao();

    @Test
    public void testGetAdvertisings() {
        // Given
        int start = 0;
        int offset = 20;
        long count = 100;
        Queries query = Queries.FIND_ADVERTISINGS;
        Map<String, Object> parameters = Collections.emptyMap();
        PagedSearch search = new PagedSearch(start, offset, query, parameters);
        Advertising entity1 = new Advertising();
        List<Advertising> entities = Arrays.asList(entity1);
        PagedResult<Advertising> expected = new PagedResult<Advertising>(start, offset, count, entities);
        String querySql = String.format(COUNT_QUERY, search.getQuery().getQuery()).replace(TABLE_TOKEN, Advertising.class.getSimpleName());
        
        // When
        when(entityManager.createNamedQuery(anyString(), Matchers.<Class<Advertising>>any())).thenReturn(typedQuery);
        when(entityManager.createQuery(anyString(), Matchers.<Class<Long>>any())).thenReturn(longTypedQuery);
        when(typedQuery.getResultList()).thenReturn(entities);
        when(longTypedQuery.getSingleResult()).thenReturn(count);
        
        PagedResult<Advertising> actual = unit.getAdvertisings(start, offset);
        
        // Then
        verify(entityManager).createNamedQuery(query.getQueryName(), Advertising.class);
        verify(entityManager).createQuery(querySql, Long.class);
        
        verify(typedQuery, times(0)).setParameter(anyString(), anyObject());
        verify(longTypedQuery, times(0)).setParameter(anyString(), anyObject());
        
        verify(typedQuery).setFirstResult(start);
        verify(typedQuery).setMaxResults(offset);
        
        assertEquals(expected, actual);
    }
}
