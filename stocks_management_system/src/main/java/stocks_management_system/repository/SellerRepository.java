package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stocks_management_system.entity.Customer;
import stocks_management_system.entity.Seller;
@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>{
	Seller findByUsername(String username);

}
