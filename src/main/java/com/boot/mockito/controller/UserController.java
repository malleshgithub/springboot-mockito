package com.boot.mockito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.mockito.model.User;
import com.boot.mockito.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/save")
	public User saveUser(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@GetMapping(value = "/getUsers")
	public List<User> findAllUsers(){
		return service.getUsers();
	}
	
	@GetMapping(value = "/getUserByAddress/{address}")
	public List<User> findUserByAddress(@PathVariable String address){
		return service.getUserByAddress(address);
	}
	
	@DeleteMapping(value = "/remove")
	public User removeUser(@RequestBody User user) {
		service.deleteUser(user);
		return user;
	}
}
