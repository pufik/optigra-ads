package org.optigra.ads.rest.web.filter.wrapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomHttpServletResponseWrapperTest {

    @Mock
    private HttpServletResponse httpResponse;

    @Test
    public void testSkipAuthenticationHeader() {
        // Given
        boolean skipHeader = true;
        String headerValue = "headerValue";
        CustomHttpServletResponseWrapper unit = new CustomHttpServletResponseWrapper(httpResponse, skipHeader);

        // When
        unit.addHeader("WWW-Authenticate", headerValue);
        verifyZeroInteractions(httpResponse);
    }

    @Test
    public void testAddAuthenticationHeader() {
        // Given
        boolean skipHeader = false;
        String headerValue = "headerValue";
        CustomHttpServletResponseWrapper unit = new CustomHttpServletResponseWrapper(httpResponse, skipHeader);

        // When
        String headerName = "WWW-Authenticate";
        unit.addHeader(headerName, headerValue);
        verify(httpResponse).addHeader(headerName, headerValue);
    }
}
