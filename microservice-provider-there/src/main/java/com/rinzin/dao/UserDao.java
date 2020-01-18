package com.rinzin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rinzin.entity.User;

@Mapper
public interface UserDao {

	public boolean addUser(User user);
	
	public User getUser(int id);
	
	public List<User> getUsers();
}
