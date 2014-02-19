package org.optigra.ads.configuration;

/**
 * Application configuration service.
 *
 * @date Feb 18, 2014
 * @author Iurii Parfeniuk
 */
public interface ConfigurationService {

    /**
     * Get application property.
     *
     * @date Feb 18, 2014
     * @author Iurii Parfeniuk
     * @param propertyName
     *            - set property name
     * @return property value
     */
    String getProperty(String propertyName);

}
