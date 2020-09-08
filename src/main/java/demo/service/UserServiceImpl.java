package demo.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.entity.User;
import demo.model.UserBO;
import demo.repository.UserRepository;

/**
 * Implementation class of UserService
 * 
 * @author ganeshthorat
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * User to UserBO mapper function
	 */
	private Function<User, UserBO> mapUserToUserBO = new Function<User, UserBO>() {

		public UserBO apply(User user) {
			UserBO userBO = new UserBO();
			userBO.setId(user.getId());
			userBO.setName(user.getName());
			userBO.setEmail(user.getEmail());
			return userBO;
		}
	};

	/** 
	 * UserBO to User mapper function
	 */
	private Function<UserBO, User> mapUserBOToUser = new Function<UserBO, User>() {

		public User apply(UserBO userBO) {
			User user = new User();
			user.setId(userBO.getId());
			user.setName(userBO.getName());
			user.setEmail(userBO.getEmail());
			return user;
		}
	};

	@Override
	public List<UserBO> findAll() {
		return ((List<User>) userRepository.findAll()).stream().map(mapUserToUserBO).collect(Collectors.toList());
	}

	@Override
	public void save(UserBO userBO) {
		userRepository.save(mapUserBOToUser.apply(userBO));
	}

	@Override
	public void deleteById(long id) {
		userRepository.deleteById(id);
	}

}
