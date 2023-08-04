package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Product;
import stocks_management_system.entity.PurchaseProduct;
import stocks_management_system.entity.User;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.PurchaseProductRepository;
import stocks_management_system.repository.UserRepository;
import stocks_management_system.request.PurchaseProductRequest;
import stocks_management_system.responce.PurchaseProductResponce;

@Component
public class PurchaseProductHelper {
	@Autowired
	PurchaseProductRepository purchaseProductRepository;
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	
	public PurchaseProduct toEntity(PurchaseProductRequest purchaseProductRequest) {
		PurchaseProduct purchaseProduct;
//		PurchaseProduct purchaseProduct= new PurchaseProduct();
		purchaseProduct = purchaseProductRepository.findByUserIdAndProductId(purchaseProductRequest.getUserId(),
				purchaseProductRequest.getProductId());
		if (purchaseProduct == null) {
			purchaseProduct = new PurchaseProduct();
		}
		purchaseProduct.setStockQuantity(purchaseProductRequest.getStockQuantity());

		Product product = productRepository.findById(purchaseProductRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));
		purchaseProduct.setProduct(product);

		User user = userRepository.findById(purchaseProductRequest.getUserId())
				.orElseThrow(() -> new CustomNewException("User id not found"));
		purchaseProduct.setUser(user);

		return purchaseProduct;
	}
	public PurchaseProductResponce toDto(PurchaseProduct purchaseProduct)
	{
		PurchaseProductResponce purchaseProductResponce= new PurchaseProductResponce();
		
		purchaseProductResponce.setId(purchaseProduct.getId());
		purchaseProductResponce.setStockQuantity(purchaseProduct.getStockQuantity());
		
		purchaseProductResponce.setProductId(purchaseProduct.getProduct().getId());
		purchaseProductResponce.setName(purchaseProduct.getProduct().getName());
		purchaseProductResponce.setDescription(purchaseProduct.getProduct().getDescription());
		purchaseProductResponce.setSellingPrice(purchaseProduct.getProduct().getSellingPrice());
		purchaseProductResponce.setPurchasePrice(purchaseProduct.getProduct().getPurchasePrice());
		
		purchaseProductResponce.setUserId(purchaseProduct.getUser().getId());
		purchaseProductResponce.setUserEntityName(purchaseProduct.getUser().getName());
		purchaseProductResponce.setUsername(purchaseProduct.getUser().getUsername());
		purchaseProductResponce.setEmail(purchaseProduct.getUser().getEmail());
		purchaseProductResponce.setAddress(purchaseProduct.getUser().getAddress());
		purchaseProductResponce.setPassword(purchaseProduct.getUser().getPassword());
		
		return purchaseProductResponce;
	}
}
