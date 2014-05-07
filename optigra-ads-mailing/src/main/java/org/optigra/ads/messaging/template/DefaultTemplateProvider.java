package org.optigra.ads.messaging.template;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

@Component("templateProvider")
public class DefaultTemplateProvider implements TemplateProvider {

	@Resource(name = "handlebars")
	private Handlebars handlebars;
	
	@Override
	public <T> String process(String templateId, T data) throws Exception {
		Template template = handlebars.compile(templateId);
		return template.apply(data);
	}

}
