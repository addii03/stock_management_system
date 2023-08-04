package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Customer;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.CustomerRepository;
import stocks_management_system.request.CustomerRequest;
import stocks_management_system.responce.CustomerResponce;

@Component
public class CustomerHelper {

	@Autowired
	CustomerRepository customerRepository;
	
	public Customer toEntity(CustomerRequest customerRequest)
	{
		Customer customer= new Customer();
		if (customerRequest.getId()!= null)
		{
			customer =	customerRepository.findById(customerRequest.getId()).orElseThrow(()->new CustomNewException("customer id not present"));
		}
		customer.setName(customerRequest.getName());
		customer.setAddress(customerRequest.getAddress());
		customer.setUsername(customerRequest.getUsername());
		customer.setPassword(customerRequest.getPassword());
		return customer;
	}
	
	public CustomerResponce toDto(Customer customer)
	{
		CustomerResponce customerResponce= new CustomerResponce();
		customerResponce.setId(customer.getId());
		customerResponce.setName(customer.getName());
		customerResponce.setAddress(customer.getAddress());
		customerResponce.setUsername(customer.getUsername());
		customerResponce.setPassword(customer.getPassword());
		return customerResponce;
	}
}
