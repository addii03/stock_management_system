package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Seller;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.request.SellerRequest;
import stocks_management_system.responce.SellerResponce;

@Component
public class SellerHelper {


	@Autowired
	ProductRepository productRepository;
	@Autowired
	SellerRepository sellerRepository;
	
	public Seller toEntity (SellerRequest sellerRequest)
	{
		Seller seller= new Seller();
	 if (sellerRequest.getId()!=null)
	 {
		 seller= sellerRepository.findById(sellerRequest.getId()).orElseThrow(()-> new CustomNewException("seller id not found"));
			
	 }
	 seller.setName(sellerRequest.getName());
	 seller.setLocation(sellerRequest.getLocation());
	 seller.setUsername(sellerRequest.getUsername());
	 seller.setPassword(sellerRequest.getPassword());
	 
	
	 return seller;
	}
	
	public SellerResponce  toDto(Seller seller)
	{
		SellerResponce sellerResponce= new SellerResponce();
		sellerResponce.setId(seller.getId());
		sellerResponce.setName(seller.getName());
		sellerResponce.setLocation(seller.getLocation());
		sellerResponce.setUsername(seller.getUsername());
		sellerResponce.setPassword(seller.getPassword());
		
		return sellerResponce;
	}
}
