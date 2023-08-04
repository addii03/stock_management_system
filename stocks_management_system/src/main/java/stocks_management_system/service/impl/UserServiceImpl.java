package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.User;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.UserHelper;
import stocks_management_system.repository.UserRepository;
import stocks_management_system.request.UserRequest;
import stocks_management_system.responce.UserResponce;
import stocks_management_system.servicee.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserHelper userHelper;

	@Override
	public java.lang.String  authenticateUser(UserRequest userRequest) throws CustomNewException
	{
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		 User username = userRepository.findByUsername(userRequest.getUsername());

//		if (optUser.isPresent()) {
//			User dbuser = optUser.get();
			if (bcrypt.matches(userRequest.getPassword(), username.getPassword()))
			{
				return "User Login Successfully";
			}
			else {
				return "Incorrect Password";
			}

//		}
//		throw new CustomNewException("User is not found with this id");
	}

	@Override
	public java.lang.String createUser(UserRequest userRequest) {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		java.lang.String  encryptedPwd = bcrypt.encode(userRequest.getPassword());
		userRequest.setPassword(encryptedPwd);

		User user = userHelper.toEntity(userRequest);
		User save = userRepository.save(user);
		return save.getId() + ": User added to database Successfully";

	}

	@Override
	public List<UserResponce> getAll() {
		List<User> userList = userRepository.findAll();
		return	userList.stream().map(userHelper :: toDto).collect(Collectors.toList());
		
	}

	@Override
	public User findByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}	

}
