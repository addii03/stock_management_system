package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Product;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.request.ProductRequest;
import stocks_management_system.responce.ProductResponce;

@Component
public class ProductHelper {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SellerRepository sellerRepository;
	
	@Autowired
	SellerHelper sellerHelper;
	public Product toEntity(ProductRequest productRequest)
	{
		Product product= new Product();
		if (productRequest.getId()!=null)
		{
			productRepository.findById(productRequest.getId()).orElseThrow(()-> new CustomNewException("product id not found"));
		}
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		product.setSellingPrice(productRequest.getSellingPrice());
		product.setPurchasePrice(productRequest.getPurchasePrice());		
		
	return product;
	}
	public ProductResponce toDto(Product product)
	{
		ProductResponce productResponce= new ProductResponce();
		productResponce.setId(product.getId());
		productResponce.setName(product.getName());
		productResponce.setDescription(product.getDescription());
		productResponce.setPurchasePrice(product.getPurchasePrice());
		productResponce.setSellingPrice(product.getSellingPrice());
		
		return productResponce;
	}
	
	
}
