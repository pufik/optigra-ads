package org.optigra.ads.rest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.optigra.ads.model.User;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

	private final BaseController unit = new BaseController();
	
    private MockMvc mockMvc;
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetUser() throws Exception {
        long userId = 10L;
        User user = new User();
        user.setId(userId);
        
    	// When
        mockMvc.perform(get("/api"))
    		.andExpect(status().isOk())
    		.andExpect(content().string(objectMapper.writeValueAsString(user)));
    }
}
