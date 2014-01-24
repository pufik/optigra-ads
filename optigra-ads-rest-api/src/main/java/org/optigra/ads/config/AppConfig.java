package org.optigra.ads.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Class for Spring Application Context configuration.
 *
 * @author Ivan Ursul
 */

@Configuration
@ComponentScan(basePackages = { "org.optigra.ads.dao", "org.optigra.ads.service", "org.optigra.ads.facade" })
public class AppConfig {

}
