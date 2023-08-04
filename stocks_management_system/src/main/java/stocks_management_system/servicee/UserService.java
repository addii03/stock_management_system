package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.User;
import stocks_management_system.request.UserRequest;
import stocks_management_system.responce.UserResponce;

public interface UserService {

	String createUser(UserRequest userRequest);

	String authenticateUser(UserRequest userRequest);

	List<UserResponce> getAll();
	
	User findByUsername(String username);
}
