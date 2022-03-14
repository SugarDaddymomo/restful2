package com.tothenew.sharda.UserStuff;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class UserService {
	
	private static List<User> usersList = new ArrayList<>();
	private static int userCount = 3;
	
	static {
		usersList.add(new User(1, "test", "Test Kumar"));
		usersList.add(new User(2, "user", "USer singh"));
		usersList.add(new User(3, "pool", "Pool Thakur"));
	}
	
	public List<User> findAll() {
		return usersList;
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
	
	public User deleteById(Integer id) {
		Iterator<User> iterator = usersList.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}