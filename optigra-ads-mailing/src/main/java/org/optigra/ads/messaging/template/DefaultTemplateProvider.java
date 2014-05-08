package org.optigra.ads.messaging.template;

import java.io.IOException;

import javax.annotation.Resource;

import org.optigra.ads.messagin.exception.MailException;
import org.springframework.stereotype.Component;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

@Component("templateProvider")
public class DefaultTemplateProvider implements TemplateProvider {

	@Resource(name = "handlebars")
	private Handlebars handlebars;
	
	@Override
	public String process(String templateId, Object data) {
		String renderedTemplate;
		try {
			Template template = handlebars.compile(templateId);
			renderedTemplate = template.apply(data);
		} catch (IOException e) {
			throw new MailException();
		}
		
		return renderedTemplate;
	}

}
