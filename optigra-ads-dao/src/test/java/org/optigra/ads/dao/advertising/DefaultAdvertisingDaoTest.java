package org.optigra.ads.dao.advertising;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.dao.Query;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.dao.persistence.PersistenceManager;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.advertising.Advertising;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;

@RunWith(MockitoJUnitRunner.class)
public class DefaultAdvertisingDaoTest {

    @Mock
    private PersistenceManager<Advertising, Long> persistenceManager;

    @InjectMocks
    private final DefaultAdvertisingDao unit = new DefaultAdvertisingDao();

    @Test
    public void testGetAdvertisings() {
        // Given
        int offset = 0;
        int limit = 20;
        long count = 100;
        User user1 = new User();
        user1.setRole(UserRole.ADMIN);
        Queries query = Queries.FIND_ADVERTISINGS;
        Map<String, Object> parameters = Collections.emptyMap();
        Query<Advertising> jpQuery = new Query<Advertising>(Advertising.class, query.getQuery(), parameters);
        PagedSearch<Advertising> search = new PagedSearch<>(offset, limit, jpQuery);

        Advertising entity1 = new Advertising();
        List<Advertising> entities = Arrays.asList(entity1);
        PagedResult<Advertising> expected = new PagedResult<Advertising>(offset, limit, count, entities);

        // When
        when(persistenceManager.search(search)).thenReturn(expected);

        PagedResult<Advertising> actual = unit.getAdvertisings(offset, limit);

        // Then
        verify(persistenceManager).search(search);

        assertEquals(expected, actual);
    }
}
