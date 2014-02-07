package org.optigra.ads.facade.converter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for all converters.
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public abstract class AbstractConverter<Source, Target> implements Converter<Source, Target> {

    @Override
    public List<Target> convertAll(final List<Source> sources) {
        List<Target> targets = new ArrayList<>(sources.size());
        
        for(Source source : sources) {
            targets.add(convert(source));
        }
        
        return targets;
    }

}
