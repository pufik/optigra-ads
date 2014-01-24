package org.optigra.ads.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Class for Spring Dispatcher servlet.
 *
 * @author Ivan Ursul
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.optigra.ads.rest" })
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * Method, that is configuring message converters.
     *
     * @param converters
     *            Converters, that will be configured
     * @date Jan 21, 2014
     * @author ivanursul
     */
    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
        addDefaultHttpMessageConverters(converters);
    }

    /**
     * Jackson Message Converter.
     *
     * @date Jan 21, 2014
     * @author ivanursul
     * @return default converter
     */
    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(mediaTypes());
        converter.setPrettyPrint(Boolean.TRUE);

        return converter;
    }

    /**
     * Supported media types.
     *
     * @date Jan 21, 2014
     * @author ivanursul
     * @return supported media types
     */
    @Bean
    public List<MediaType> mediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON);
    }
}
