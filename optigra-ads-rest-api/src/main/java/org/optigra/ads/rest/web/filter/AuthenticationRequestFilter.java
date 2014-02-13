package org.optigra.ads.rest.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.optigra.ads.rest.web.filter.wrapper.CustomHttpServletResponseWrapper;
import org.springframework.util.StringUtils;

/**
 * Filter for handling request from browsers and script clients
 *
 * @date Feb 13, 2014
 * @author Iurii Parfeniuk
 */
public class AuthenticationRequestFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        httpServletResponse = getCustomServletResponseWrapper(httpServletRequest, httpServletResponse);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private HttpServletResponse getCustomServletResponseWrapper(final HttpServletRequest request, final HttpServletResponse response) {

        if (!StringUtils.isEmpty(request.getHeader(Constants.X_REQUESTED_WITH_HEADER))) {
            return new CustomHttpServletResponseWrapper(response, Boolean.TRUE.booleanValue());
        }

        return response;
    }

    @Override
    public void init(final FilterConfig init) throws ServletException {
    }

}
