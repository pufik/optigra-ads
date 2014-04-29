package org.optigra.ads.facade.converter.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.optigra.ads.facade.resource.user.UserDetailsResource;
import org.optigra.ads.model.user.User;
import org.optigra.ads.model.user.UserRole;


public class PutUserDetailsResourceConverterTest {

	private PutUserDetailsResourceConverter unit = new PutUserDetailsResourceConverter();
	
	
	@Test
	public void testConvert() throws Exception {
		// Given
		Long id = 1L;
		String email = "email";
		String fullName = "full name";
		String imageUrl = "image url";
		String login = "login";
		String password = "password";
		UserRole role = UserRole.ADMIN;
		
		UserDetailsResource source = new UserDetailsResource();
		source.setEmail(email);
		source.setFullName(fullName);
		source.setImageUrl(imageUrl);
		source.setLogin(login);
		source.setPassword(password);
		source.setRole(role);
		source.setId(id);
		
		User expected = new User();
		expected.setEmail(email);
		expected.setFullName(fullName);
		expected.setImageUrl(imageUrl);
		expected.setLogin(login);
		expected.setRole(role);
		
		// When
		User actual = unit.convert(source);

		// Then
		assertEquals(expected, actual);
	}
}
