package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stocks_management_system.entity.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	Order findByProductIdAndSellerId(Long productId, Long sellerId);

}
