package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stocks_management_system.entity.PurchaseProduct;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct,Long>{

	PurchaseProduct findByProductId(Long id);

	PurchaseProduct findByUserIdAndProductId(Long userId, Long productId);

//	PurchaseProduct findByUserIdIdAndProductIdId(Long userId, Long productId);
}
