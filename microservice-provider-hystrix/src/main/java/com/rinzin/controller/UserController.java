package com.rinzin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.rinzin.entity.User;
import com.rinzin.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public boolean addUser(@RequestBody User user){
		boolean flag = userServiceImpl.addUser(user);
		return flag;
	}
	
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	/**
	 * 当调用该方法的出现异常，就会调用对应的fallbackMethod
	 * @param id
	 * @return
	 */
	@HystrixCommand(fallbackMethod="hystrixGetUser")
	public User getUser(@PathVariable("id") int id){
		User user = userServiceImpl.getUser(id);
		if(user == null){
			throw new RuntimeException("用户id为："+id+"不存在");
		}
		return user;
	}
	
	@RequestMapping(value="/getUser/list", method=RequestMethod.GET)
	public List<User> getUsers(){
		List<User> users = userServiceImpl.getUsers();
		return users;
	}
	
	@RequestMapping(value="/getTest", method=RequestMethod.GET)
	public String getTest(){
		return "test";
	}
	public User hystrixGetUser(@PathVariable("id") int id){
		User user = new User(id, "不存在该用户", 0);
		return user;
	}
}
