package org.optigra.ads.rest.web.filter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.rest.web.filter.wrapper.CustomHttpServletResponseWrapper;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationRequestFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Captor
    private ArgumentCaptor<ServletResponse> resultServletResponseCaptor;

    @Test
    public void testDoFilter() throws IOException, ServletException {
        // Given
        String value = "headerValue";
        AuthenticationRequestFilter unit = new AuthenticationRequestFilter();
        when(request.getHeader(eq(Constants.X_REQUESTED_WITH_HEADER))).thenReturn(value);

        // When
        unit.doFilter(request, response, filterChain);

        // Then
        verify(filterChain).doFilter(eq(request), resultServletResponseCaptor.capture());
        assertTrue(resultServletResponseCaptor.getValue() instanceof CustomHttpServletResponseWrapper);
    }
}
