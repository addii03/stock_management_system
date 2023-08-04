package stocks_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stocks_management_system.repository.UserRepository;
import stocks_management_system.request.UserRequest;
import stocks_management_system.responce.UserResponce;
import stocks_management_system.servicee.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/create")
	public String createUser( @RequestBody UserRequest userRequest)
	{
		return userService.createUser(userRequest);
	}
	
	@PostMapping("/authenticateUser")
	public String authenticateUser(@RequestBody UserRequest userRequest)
	{
		return userService.authenticateUser(userRequest);
	}
	@GetMapping("/getAllUsers")
	public List<UserResponce> getAll()
	{
		return userService.getAll();
	}
}
