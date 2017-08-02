package yesdoing.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import yesdoing.domain.User;

@Repository
public class UserRepository {
	ArrayList<User> users = new ArrayList<>();
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public User getUser(int index) {
		return users.get(index);
	}
}
