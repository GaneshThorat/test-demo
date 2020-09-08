package demo.service;

import java.util.List;

import demo.model.UserBO;

/** 
 * User service interface which interacts between controller and repository
 * 
 * @author ganeshthorat
 *
 */
public interface UserService {
	
	/**
	 * find all users
	 * @return List - UserBO list
	 */
	public List<UserBO> findAll() ;
	
	/**
	 * saves the user in DB
	 * @param userBO - UserBO object
	 */
	public void save(UserBO userBO);
	
	/**
	 * deletes the User record by id
	 * @param id - id of user record
	 */
	public void deleteById(long id);

}
