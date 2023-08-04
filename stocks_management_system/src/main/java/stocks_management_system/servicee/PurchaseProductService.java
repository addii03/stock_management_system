package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.PurchaseProduct;
import stocks_management_system.request.PurchaseProductRequest;
import stocks_management_system.responce.PurchaseProductResponce;

public interface PurchaseProductService {

	PurchaseProductResponce purchaseProduct(PurchaseProductRequest purchaseProductRequest);
	PurchaseProduct findByProductId(Long id);
	List<PurchaseProductResponce> getAll();
	
}
