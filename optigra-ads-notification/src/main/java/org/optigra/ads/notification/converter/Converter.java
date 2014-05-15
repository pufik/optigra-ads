package org.optigra.ads.notification.converter;


public interface Converter<Source, Target> {

    Target convert(Source source);

}
