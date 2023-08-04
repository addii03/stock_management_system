package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.User;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.UserRepository;
import stocks_management_system.request.UserRequest;
import stocks_management_system.responce.UserResponce;

@Component
public class UserHelper {
	@Autowired
	UserRepository userRepository;
	
	public User toEntity(UserRequest userRequest)
	{
		User user= new  User();
		if (userRequest.getId()!=null)
		{
			user=	userRepository.findById(userRequest.getId()).orElseThrow(()-> new CustomNewException("user id not found"));
		}
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setAddress(userRequest.getAddress());
		user.setName(userRequest.getName());
		user.setUsername(userRequest.getUsername());
		user.setPassword(userRequest.getPassword());
		return user;
		
	}
	public UserResponce toDto(User user)
	{
		UserResponce userResponce= new UserResponce();
		userResponce.setId(user.getId());
		userResponce.setName(user.getName());
		userResponce.setEmail(user.getEmail());
		userResponce.setAddress(user.getAddress());
		userResponce.setUsername(user.getUsername());
		userResponce.setPassword(user.getPassword());
		
		return userResponce;
		
	}
	
}
