package com.rinzin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rinzin.entity.User;

@RestController
@RequestMapping("/consumer")
public class UserConsumerController {
	//private static String REST_URL_PREFIX = "http://localhost:8002/user";
	private static String REST_URL_PREFIX = "http://microservice-provider/user";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/add")
	public boolean addUser(User user){
		Boolean flag = restTemplate.postForObject(REST_URL_PREFIX + "/add", user, Boolean.class);
		return flag;
	}
	
	@RequestMapping(value="/get/{id}")
	public User get(@PathVariable("id") int id){
		User user = restTemplate.getForObject(REST_URL_PREFIX + "/get" + "/"+id, User.class);
		return user;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/list")
	public List<User> getList(){
		List list = restTemplate.getForObject(REST_URL_PREFIX + "/getUser/list", List.class);
		return list;
	}
}
