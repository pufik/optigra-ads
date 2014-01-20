package org.optigra.ads.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Class for Spring Dispatcher servlet.
 *
 * @author Ivan Ursul
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.optigra.ads.rest" })
public class WebConfig {

}
