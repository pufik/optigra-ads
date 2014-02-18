package org.optigra.ads.configuration;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * Default configuration service.
 *
 * @date Feb 18, 2014
 * @author Iurii Parfeniuk
 */

@Service("configurationService")
public class DefaultConfigurationService implements ConfigurationService {

    @Resource(name = "applicationConfiguration")
    private Properties applicationProperties;

    @Override
    public String getProperty(final String propertyName) {
        return applicationProperties.getProperty(propertyName);
    }

}
