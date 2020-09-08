package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.entity.User;

/**
 * User Repository which interacts with in memory DB
 * @author ganeshthorat
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
