package demo.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import demo.entity.User;
import demo.model.UserBO;
import demo.repository.UserRepository;
import demo.service.UserServiceImpl;

public class UserServiceTest {
	
	 @InjectMocks
	  UserServiceImpl service;
	 
	 @Mock
	 UserRepository userRepository;
	 
	 @Before
	    public void init() {
	        MockitoAnnotations.initMocks(this);
	    }
	 
	 @Test
	 public void findAll() {
		
		 //given
		 UserBO alex = new UserBO (1, "alex", "test@domain.com");
		 User alexUser = new User ("alex", "test@domain.com");
		 alexUser.setId(1);
		 
		 
		 //when
		 when(userRepository.findAll()).thenReturn(Arrays.asList(alexUser));
		 
		 //then
		 List<UserBO> userBOList = service.findAll();
		 
		 Assert.assertEquals(1, userBOList.size());
		 Assert.assertEquals(alex.getName(), userBOList.get(0).getName());
	 }
	 
	 @Test
	 public void save() {
		
		 //given
		 UserBO alex = new UserBO (1, "alex", "test@domain.com");
		 User alexUser = new User ("alex", "test@domain.com");
		 alexUser.setId(1);
		 
		 
		 //when
		 when(userRepository.save(alexUser)).thenReturn(alexUser);	
		 
		 //then
		 service.save(alex);
		 
	 }
	 
	 @Test
	 public void deleteUser() {
		
		 //given
		 long id = Long.valueOf(2);
		 
		 //when
		 doNothing().when(userRepository).deleteById(id);
		 
		 //then
		 service.deleteById(id);
		 
	 }

}
