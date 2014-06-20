package org.optigra.ads.rest.web.filter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.configuration.ConfigurationService;

@RunWith(MockitoJUnitRunner.class)
public class CorsFilterTest {

    @Mock
    private ConfigurationService configurationService;

    @Mock
    private FilterChain chain;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private final CorsFilter unit = new CorsFilter();

    @Test
    public void testDoFilter() throws Exception {
        // Given
        String allowOrigins = "allowOrigins";
        String allowCredentials = "true";

        // When
        when(configurationService.getProperty(CorsFilter.CORS_ALLOWED_ORIGINS)).thenReturn(allowOrigins);
        when(configurationService.getProperty(CorsFilter.CORS_SUPPORT_CREDENTIALS)).thenReturn(allowCredentials);

        unit.doFilter(request, response, chain);

        // Then
        verify(configurationService).getProperty(CorsFilter.CORS_ALLOWED_ORIGINS);
        verify(configurationService).getProperty(CorsFilter.CORS_SUPPORT_CREDENTIALS);
        verify(response).setHeader(Constants.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, allowOrigins);
        verify(response).setHeader(Constants.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, allowCredentials);
    }

    @Test
    public void testDoFilterWhenPreFlight() throws Exception {
        // Given
        String alloHeaders = "Allow Headers";
        String allowMethods = "allowMethods";
        String allowOrigins = "allowOrigins";
        String maxAge = "1600";
        String allowCredentials = "true";
        String httpMethodGet = "GET";
        String httpOptions = "OPTIONS";

        // When
        when(configurationService.getProperty(CorsFilter.CORS_ALLOWED_HEADERS)).thenReturn(alloHeaders);
        when(configurationService.getProperty(CorsFilter.CORS_ALLOWED_METHODS)).thenReturn(allowMethods);
        when(configurationService.getProperty(CorsFilter.CORS_ALLOWED_ORIGINS)).thenReturn(allowOrigins);
        when(configurationService.getProperty(CorsFilter.CORS_PREFLIGHT_MAXAGE)).thenReturn(maxAge);
        when(configurationService.getProperty(CorsFilter.CORS_SUPPORT_CREDENTIALS)).thenReturn(allowCredentials);
        when(request.getHeader(Constants.ACCESS_CONTROL_REQUEST_METHOD_HEADER)).thenReturn(httpMethodGet);
        when(request.getMethod()).thenReturn(httpOptions );

        unit.doFilter(request, response, chain);

        // Then
        verify(request).getHeader(Constants.ACCESS_CONTROL_REQUEST_METHOD_HEADER);
        verify(configurationService).getProperty(CorsFilter.CORS_ALLOWED_HEADERS);
        verify(configurationService).getProperty(CorsFilter.CORS_ALLOWED_METHODS);
        verify(configurationService).getProperty(CorsFilter.CORS_ALLOWED_ORIGINS);
        verify(configurationService).getProperty(CorsFilter.CORS_PREFLIGHT_MAXAGE);
        verify(configurationService).getProperty(CorsFilter.CORS_SUPPORT_CREDENTIALS);
    }
}
