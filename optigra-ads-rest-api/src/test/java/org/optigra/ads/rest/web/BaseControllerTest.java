package org.optigra.ads.rest.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(MockitoJUnitRunner.class)
public class BaseControllerTest {

	private BaseController unit = new BaseController();
	
    private MockMvc mockMvc;
    

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(unit).build();
    }

    @Test
    public void testGetUser() throws Exception {
    	// When
    	mockMvc.perform(get("/"))
    		.andExpect(status().isOk());
    }
}
