package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Product;
import stocks_management_system.entity.Seller;
import stocks_management_system.entity.SellerStocks;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.repository.SellerStocksRepository;
import stocks_management_system.request.SellerStocksRequest;
import stocks_management_system.responce.SellerStocksResponce;

@Component
public class SellerStocksHelper {

	@Autowired
	SellerStocksRepository sellerStocksRepository;
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	ProductRepository productRepository;

	public SellerStocks toEntity(SellerStocksRequest sellerStocksRequest) {
		SellerStocks sellerStocks;

		sellerStocks = sellerStocksRepository.findByProductIdAndSellerId(sellerStocksRequest.getProductId(),
				sellerStocksRequest.getSellerId());
		if (sellerStocks == null) {
			sellerStocks = new SellerStocks();
		}

		sellerStocks.setStocks(sellerStocksRequest.getStocks());
		
		// seller
		Seller seller = sellerRepository.findById(sellerStocksRequest.getSellerId())
				.orElseThrow(() -> new CustomNewException("seller id not found"));
		sellerStocks.setSeller(seller);
		// product

		Product product = productRepository.findById(sellerStocksRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));
		sellerStocks.setProduct(product);
		return sellerStocks;
	}

	public SellerStocksResponce toDto(SellerStocks sellerStocks) {
		SellerStocksResponce sellerStocksResponce = new SellerStocksResponce();

		sellerStocksResponce.setId(sellerStocks.getId());
		sellerStocksResponce.setStocks(sellerStocks.getStocks());

		sellerStocksResponce.setProductId(sellerStocks.getProduct().getId());
		sellerStocksResponce.setProductName(sellerStocks.getProduct().getName());
		sellerStocksResponce.setDescription(sellerStocks.getProduct().getDescription());
		sellerStocksResponce.setSellingPrice(sellerStocks.getProduct().getSellingPrice());
		sellerStocksResponce.setPurchasePrice(sellerStocks.getProduct().getPurchasePrice());

		sellerStocksResponce.setSellerId(sellerStocks.getSeller().getId());
		sellerStocksResponce.setName(sellerStocks.getSeller().getName());
		sellerStocksResponce.setLocation(sellerStocks.getSeller().getLocation());
		sellerStocksResponce.setUsername(sellerStocks.getSeller().getUsername());
		sellerStocksResponce.setPassword(sellerStocks.getSeller().getPassword());
		return sellerStocksResponce;
	}
}
