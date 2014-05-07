package org.optigra.ads.messaging.template;

public interface TemplateProvider {

	<T> String process(String templateId, T data) throws Exception;

}
