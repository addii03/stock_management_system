package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stocks_management_system.entity.SellerStocks;

@Repository
public interface SellerStocksRepository extends JpaRepository<SellerStocks, Long>{

	SellerStocks findByProductIdAndSellerId(Long productId, Long sellerId);
	SellerStocks findByProductId(Long productId);
}
