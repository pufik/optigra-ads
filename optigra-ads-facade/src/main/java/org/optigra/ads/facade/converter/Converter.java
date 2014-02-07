package org.optigra.ads.facade.converter;

import java.util.List;

/**
 * Interface, that declares converting functions.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public interface Converter<Source, Target> {

    /**
     * Convert one-to-one.
     * @date Feb 7, 2014
     * @author ivanursul
     * @param source
     * @return
     */
    Target convert(Source source);
    /**
     * Convert many-to-many.
     * @date Feb 7, 2014
     * @author ivanursul
     * @param sources
     * @return
     */
    List<Target> convertAll(List<Source> sources);
    
}
