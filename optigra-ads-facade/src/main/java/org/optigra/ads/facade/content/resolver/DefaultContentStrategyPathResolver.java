package org.optigra.ads.facade.content.resolver;

import org.optigra.ads.content.model.ContentStrategy;
import org.springframework.stereotype.Component;

@Component("contentStrategyPathResolver")
public class DefaultContentStrategyPathResolver implements ContentStrategyPathResolver {

    @Override
    public String getPath(final ContentStrategy contentStrategy) {
        
        String path =  contentStrategy.getPath();
        
        return path;
    }

}
