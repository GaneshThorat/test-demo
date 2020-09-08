package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.model.UserBO;
import demo.service.UserServiceImpl;

/**
 * Controller class for User API
 * @author ganeshthorat
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	/**
	 * get all the users
	 * @return List - list of UserBO
	 */
    @GetMapping("/users")
    public List<UserBO> getUsers() {
        return  userService.findAll();
    }
 
    /**
     * add the new users
     * @param userBO - userBO object
     */
    @PostMapping("/users")
    void addUser(@RequestBody UserBO userBO) {
    	userService.save(userBO);
    }
    
    
    /**
     * deletes the user by id
     * @param id - long value of UserBO
     */
    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable long id) {
    	userService.deleteById(id);
    }

}
