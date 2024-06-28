package com.boot.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.boot.mockito.dao.UserRepository;
import com.boot.mockito.model.User;
import com.boot.mockito.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringbootMockitoApplicationTests {
	
	@Autowired
	private UserService service; 
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
	}
	
	@Test
	public void getUserByAddressTest() {
		String address="Hyderabad";
		when(repository.findByAddress(address)).thenReturn(Stream
				.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
		assertEquals(1, repository.findByAddress(address).size());
	}
	
	@Test
	public void saveUserTest() {
		User user=new User(998, "Rohan", 32, "HYD");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}
	
	@Test
	public void deleteUserTest() {
		User user=new User(998, "Rohan", 32, "HYD");
		service.deleteUser(user);
		verify(repository, times(1)).delete(user);
	}
}
