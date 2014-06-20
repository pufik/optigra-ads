package org.optigra.ads.rest.web.filter;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.optigra.ads.configuration.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

public class CorsFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    protected static final String CORS_PREFLIGHT_MAXAGE = "cors.preflight.maxage";
    protected static final String CORS_ALLOWED_HEADERS = "cors.allowed.headers";
    protected static final String CORS_ALLOWED_METHODS = "cors.allowed.methods";
    protected static final String CORS_SUPPORT_CREDENTIALS = "cors.support.credentials";
    protected static final String CORS_ALLOWED_ORIGINS = "cors.allowed.origins";

    @Resource(name = "configurationService")
    private ConfigurationService configurationService;

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {

        addCorsSecurityHeaders((HttpServletRequest) request, (HttpServletResponse) response);

        chain.doFilter(request, response);
    }

    protected void addCorsSecurityHeaders(final HttpServletRequest request, final HttpServletResponse response) {

        response.setHeader(Constants.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, getProperty(CORS_ALLOWED_ORIGINS));
        response.setHeader(Constants.ACCESS_CONTROL_ALLOW_CREDENTIALS_HEADER, getProperty(CORS_SUPPORT_CREDENTIALS));

        // Check CORS "pre-flight" request
        String corsRequestMethod = request.getHeader(Constants.ACCESS_CONTROL_REQUEST_METHOD_HEADER);

        if(!StringUtils.isEmpty(corsRequestMethod) && HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())){
            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_METHODS_HEADER, getProperty(CORS_ALLOWED_METHODS));
            response.setHeader(Constants.ACCESS_CONTROL_ALLOW_HEADERS_HEADER, getProperty(CORS_ALLOWED_HEADERS));
            response.setHeader(Constants.ACCESS_CONTROL_MAX_AGE_HEADER, getProperty(CORS_PREFLIGHT_MAXAGE));
        }
    }

    protected String getProperty(final String propertyName) {
        return configurationService.getProperty(propertyName);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        logger.info("Init CORS filter.");
    }

    @Override
    public void destroy() {
        logger.info("Destroyed CORS filter.");
    }
}
