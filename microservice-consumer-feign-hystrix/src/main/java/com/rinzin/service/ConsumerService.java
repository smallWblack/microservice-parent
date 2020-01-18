package com.rinzin.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rinzin.entity.User;


/*以后调用microservicecloud-provider微服务中的方法，只需要调用下面对应的接口既可以了*/
@FeignClient(value="microservice-provider", fallbackFactory=ConsumerServiceFallbackFactory.class)
public interface ConsumerService {
	/*调用接口中的get方法，即可以向microservicecloud-provider微服务发送/get/{id}请求*/
	@RequestMapping(value="/user/get/{id}", method=RequestMethod.GET)
	public User get(@PathVariable("id") int id);
	
	@RequestMapping(value="/user/add", method=RequestMethod.POST)
	public boolean add(User user);
	
	@RequestMapping(value="/user/getUser/list", method=RequestMethod.GET)
	public List<User> getAll();
}
