package org.optigra.ads.messaging.template;

public interface TemplateProvider {

	String process(String templateId, Object data);

}
