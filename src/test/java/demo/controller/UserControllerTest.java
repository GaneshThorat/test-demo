package demo.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.controller.UserController;
import demo.model.UserBO;
import demo.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean(name="userService")
    private UserServiceImpl service;
	
	@Test
	public void findAllUsers() throws Exception {
	    
	    UserBO alex = new UserBO (1, "alex", "test@domain.com");
	 
	    List<UserBO> allUsers = Arrays.asList(alex);
	 
	    when(service.findAll()).thenReturn(allUsers);
	 
	    mvc.perform(get("/users")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].name", is(alex.getName())));
	}

	@Test
	public void addUser() throws Exception {
	    
	    UserBO alex = new UserBO (1, "alex", "test@domain.com");
	 
	    doNothing().when(service).save(alex);
	    
	    mvc.perform(post("/users")
	    	      .content(new ObjectMapper().writeValueAsString(alex))
	    	      .contentType(MediaType.APPLICATION_JSON)
	    	      .accept(MediaType.APPLICATION_JSON))
	    	      .andExpect(status().isOk());
	}

	@Test
	public void deleteUser() throws Exception {
	    
	    long id = Long.valueOf(2);
	 
	    doNothing().when(service).deleteById(id);
	    
	    mvc.perform( delete("/users/{id}", id) )
        .andExpect(status().isOk());
	    }
	

}
