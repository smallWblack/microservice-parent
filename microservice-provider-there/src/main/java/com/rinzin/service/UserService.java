package com.rinzin.service;

import java.util.List;

import com.rinzin.entity.User;

public interface UserService {
	public boolean addUser(User user);
	
	public User getUser(int id);
	
	public List<User> getUsers();
}
