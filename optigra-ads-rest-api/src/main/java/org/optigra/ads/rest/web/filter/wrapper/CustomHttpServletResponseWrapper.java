package org.optigra.ads.rest.web.filter.wrapper;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.optigra.ads.rest.web.filter.Constants;

public class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private final boolean skipAuthenticationHeader;

    public CustomHttpServletResponseWrapper(final HttpServletResponse response, final boolean skipAuthenticationHeader) {
        super(response);
        this.skipAuthenticationHeader = skipAuthenticationHeader;
    }

    @Override
    public void addHeader(final String name, final String value) {
        if (!hasSkipAuthenticationHeader(name)) {
            super.addHeader(name, value);
        }
    }

    private boolean hasSkipAuthenticationHeader(final String name) {
        return Constants.AUTHENTICATION_HEADER.equals(name) && skipAuthenticationHeader;
    }
}
