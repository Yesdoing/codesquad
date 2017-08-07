package yesdoing.dao;


import org.springframework.data.repository.CrudRepository;

import yesdoing.domain.User;

public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserId(String userId);
}
