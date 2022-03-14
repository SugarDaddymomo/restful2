package com.tothenew.sharda.Filtering;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class UserServiceNew {
	
	private static List<User> usersList = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		usersList.add(new User(1, "test", "test@12345"));
		usersList.add(new User(2, "user", "user@54321"));
		usersList.add(new User(3, "pool", "pool@54#23"));
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		usersList.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		for (User user : usersList) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public List<User> findAll() {
		return usersList;
	}
}