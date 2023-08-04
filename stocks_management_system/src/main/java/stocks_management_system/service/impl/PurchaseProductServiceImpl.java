package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.Product;
import stocks_management_system.entity.PurchaseProduct;
import stocks_management_system.entity.User;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.PurchaseProductHelper;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.PurchaseProductRepository;
import stocks_management_system.repository.UserRepository;
import stocks_management_system.request.PurchaseProductRequest;
import stocks_management_system.responce.PurchaseProductResponce;
import stocks_management_system.servicee.PurchaseProductService;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService{

	@Autowired
	PurchaseProductRepository purchaseProductRepository;
	@Autowired
	PurchaseProductHelper purchaseProductHelper;
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public PurchaseProductResponce purchaseProduct(PurchaseProductRequest purchaseProductRequest) {

		// SellerStocks sellerStocks= new SellerStocks();
		Product product = productRepository.findById(purchaseProductRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));
		User user = userRepository.findById(purchaseProductRequest.getUserId())
				.orElseThrow(() -> new CustomNewException("product id not found"));
		PurchaseProduct purchaseProduct;
		purchaseProduct = purchaseProductRepository.findByUserIdAndProductId(purchaseProductRequest.getUserId(),
				purchaseProductRequest.getProductId());
		if (purchaseProduct == null) {
			purchaseProduct = new PurchaseProduct();
			purchaseProduct.setProduct(product);
			purchaseProduct.setStockQuantity(purchaseProductRequest.getStockQuantity());
			purchaseProduct.setUser(user);
		} else {
			Long stockQuantity = purchaseProduct.getStockQuantity();
			purchaseProduct.setStockQuantity(stockQuantity + purchaseProductRequest.getStockQuantity());
		}

		purchaseProductRepository.save(purchaseProduct);

		return purchaseProductHelper.toDto(purchaseProduct);
	}

	@Override
	public PurchaseProduct findByProductId(Long id) {
		return purchaseProductRepository.findByProductId(id);
	}

	@Override
	public List<PurchaseProductResponce> getAll() {
		List<PurchaseProduct> purchaseProductList = purchaseProductRepository.findAll();
		return purchaseProductList.stream().map(purchaseProductHelper::toDto).collect(Collectors.toList());
	}

}
