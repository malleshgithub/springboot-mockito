package com.boot.mockito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.mockito.dao.UserRepository;
import com.boot.mockito.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public User addUser(User user) {
		return repository.save(user);
	}
	
	public List<User> getUsers(){
		List<User> users = repository.findAll();
		System.out.println("Getting Data from DB "+users);
		return users;
	}
	
	public List<User> getUserByAddress(String address){
		return repository.findByAddress(address);
	}
	
	public void deleteUser(User user) {
		 repository.delete(user);
	}
}
