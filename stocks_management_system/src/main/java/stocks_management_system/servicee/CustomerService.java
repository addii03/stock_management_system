package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.Customer;
import stocks_management_system.request.CustomerRequest;
import stocks_management_system.responce.CustomerResponce;

public interface CustomerService {

	String createCustomer(CustomerRequest customerRequest);
	String authenticateCustomer(CustomerRequest customerRequest);
	List<CustomerResponce> getAll();
	Customer findByUsername(String username);
	
}
