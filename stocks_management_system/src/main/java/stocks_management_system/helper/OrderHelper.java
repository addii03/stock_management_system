package stocks_management_system.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import stocks_management_system.entity.Customer;
import stocks_management_system.entity.Order;
import stocks_management_system.entity.Product;
import stocks_management_system.entity.Seller;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.repository.CustomerRepository;
import stocks_management_system.repository.OrderRepository;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.request.OrderRequest;
import stocks_management_system.responce.OrderResponce;

@Component
public class OrderHelper {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	SellerRepository sellerRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CustomerRepository customerRepository;
	
	public Order toEntity(OrderRequest orderRequest)
	{
		Order order;
		order = orderRepository.findById(orderRequest.getId()).orElseThrow(() -> new CustomNewException("order id not found"));
		if(order==null)
		{
			order= new Order();
		}
		order.setQuantity(orderRequest.getQuantity());
		
		Seller seller = sellerRepository.findById(orderRequest.getSellerId())
				.orElseThrow(() -> new CustomNewException("seller id not found"));
		order.setSeller(seller);
		// product

		Product product = productRepository.findById(orderRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));
		order.setProduct(product);
		
		Customer customer= customerRepository.findById(orderRequest.getCustomerId()).orElseThrow(() -> new CustomNewException("customer id not found"));
		order.setCustomer(customer);
		return order;
		
	}
	public OrderResponce toDto(Order order)
	{
		OrderResponce orderResponce= new OrderResponce();
		orderResponce.setId(order.getId());
		orderResponce.setQuantity(order.getQuantity());
		
		orderResponce.setProductId(order.getProduct().getId());
		orderResponce.setProductName(order.getProduct().getName());
		orderResponce.setDescription(order.getProduct().getDescription());
		orderResponce.setSellingPrice(order.getProduct().getSellingPrice());
		orderResponce.setPurchasePrice(order.getProduct().getPurchasePrice());

		orderResponce.setSellerId(order.getSeller().getId());
		orderResponce.setName(order.getSeller().getName());
		orderResponce.setLocation(order.getSeller().getLocation());
		orderResponce.setUsername(order.getSeller().getUsername());
		orderResponce.setPassword(order.getSeller().getPassword());
		
		orderResponce.setCustomerId(order.getCustomer().getId());
		orderResponce.setCustomerName(order.getCustomer().getName());
		orderResponce.setAddress(order.getCustomer().getAddress());
		orderResponce.setCustomerUsername(order.getCustomer().getUsername());
		orderResponce.setCustomerpassword(order.getCustomer().getPassword());
		
		return orderResponce;
	}
}
