package org.optigra.ads.messaging.template;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.user.User;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;

@RunWith(MockitoJUnitRunner.class)
public class DefaultTemplateProviderTest {
	
	@Mock
	private Handlebars handlebars;
	
	@Mock
	private Template template;

	@InjectMocks
	private TemplateProvider unit = new DefaultTemplateProvider();
	
	@Test
	public void testProcess() throws Exception {
		// Given
		String templateId = "templateId";
		String expected = "Handlebar rendered view";
		
		// When
		when(handlebars.compile(anyString())).thenReturn(template);
		when(template.apply(any(Object.class))).thenReturn(expected);
		User data = new User();
		String actual = unit.process(templateId, data);

		// Then
		verify(handlebars).compile(templateId);
		verify(template).apply(data);
		assertEquals(expected, actual);
	}
}
