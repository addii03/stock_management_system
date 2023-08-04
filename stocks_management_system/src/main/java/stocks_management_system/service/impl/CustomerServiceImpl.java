package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.Customer;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.CustomerHelper;
import stocks_management_system.repository.CustomerRepository;
import stocks_management_system.request.CustomerRequest;
import stocks_management_system.responce.CustomerResponce;
import stocks_management_system.servicee.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerHelper customerHelper;

	@Override
	public java.lang.String authenticateCustomer(CustomerRequest customerRequest) throws CustomNewException {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		 Customer byUsername = customerRepository.findByUsername(customerRequest.getUsername());

//		if (byUsername.is) {
//			Customer dbCustomer = byUsername.get();
			if (bcrypt.matches(customerRequest.getPassword(), byUsername.getPassword()))
			{
				return "Customer Login Successfully";
			}
			else {
				return "Incorrect Password";
			}

		//throw new CustomNewException("User is not found with this id");
	}

	@Override
	public java.lang.String createCustomer(CustomerRequest customerRequest) {
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		java.lang.String encryptedPwd1 = bcrypt.encode(customerRequest.getPassword());
		customerRequest.setPassword(encryptedPwd1);
		
		Customer customer = customerHelper.toEntity(customerRequest);
		Customer save1 = customerRepository.save(customer);
		return save1.getId() +  ": Customer Added to database Successfully";
	}

	@Override
	public List<CustomerResponce> getAll() {
		List<Customer> customerList = customerRepository.findAll();
		return	customerList.stream().map(customerHelper::toDto).collect(Collectors.toList());
		
	}

	@Override
	public Customer findByUsername(String username) {
	
		return customerRepository.findByUsername(username);
	}

}
