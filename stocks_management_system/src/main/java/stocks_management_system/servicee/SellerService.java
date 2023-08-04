package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.Seller;
import stocks_management_system.request.SellerRequest;
import stocks_management_system.responce.SellerResponce;

public interface SellerService {

	String createSeller(SellerRequest sellerRequest);
	SellerResponce getById(long id);
	List<SellerResponce> getAllSeller();
	String authenticateSeller (SellerRequest sellerRequest);
	
	Seller findByUsername(String username);

}
