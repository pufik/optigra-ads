package org.optigra.ads.rest.web.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @date Feb 11, 2014
 * @author ivanursul
 *
 */
public class CustomObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -7103780836911793395L;

    public CustomObjectMapper() {
        super();
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);    
    }
    
}
