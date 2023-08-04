package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.SellerStocks;
import stocks_management_system.request.SellerStocksRequest;
import stocks_management_system.responce.SellerStocksResponce;

public interface SellerStocksService {

	SellerStocksResponce allocateStock (SellerStocksRequest sellerStocksRequest);
	SellerStocks findByProductIdAndSellerId(Long productId, Long sellerId);
	
	SellerStocks findByProductId(Long productId);
	
	List<SellerStocksResponce> getAll();
}
