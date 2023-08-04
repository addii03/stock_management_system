package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import stocks_management_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{

	Customer findByUsername(String username);
	
}
