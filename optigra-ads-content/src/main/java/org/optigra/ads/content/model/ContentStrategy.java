package org.optigra.ads.content.model;

public enum ContentStrategy {

    USER("user"),
    APPLICATION("application"), 
    DEFAULT("common");
    
    String path;
    
    ContentStrategy(final String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
