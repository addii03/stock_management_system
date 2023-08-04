package stocks_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stocks_management_system.repository.CustomerRepository;
import stocks_management_system.request.CustomerRequest;
import stocks_management_system.responce.CustomerResponce;
import stocks_management_system.servicee.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	CustomerRepository customerRepository;

	@PostMapping("/create")
	public String createCustomer(@RequestBody CustomerRequest customerRequest) {
		return customerService.createCustomer(customerRequest);
	}

	@PostMapping("/authenticateCustomer")
	public String authenticateCustomer(@RequestBody CustomerRequest customerRequest) {
		return customerService.authenticateCustomer(customerRequest);
	}

	@GetMapping("/getAllCustomer")
	public List<CustomerResponce> getAll() {
		return customerService.getAll();
	}
}
