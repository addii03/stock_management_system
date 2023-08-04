package stocks_management_system.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.Customer;
import stocks_management_system.entity.Order;
import stocks_management_system.entity.Product;
import stocks_management_system.entity.Seller;
import stocks_management_system.entity.SellerStocks;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.OrderHelper;
import stocks_management_system.repository.CustomerRepository;
import stocks_management_system.repository.OrderRepository;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.repository.SellerStocksRepository;
import stocks_management_system.request.OrderRequest;
import stocks_management_system.responce.OrderResponce;
import stocks_management_system.servicee.OrderService;
import stocks_management_system.servicee.SellerStocksService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	SellerStocksRepository sellerStocksRepository;

	@Autowired
	SellerStocksService sellerStocksService;
	@Autowired
	OrderHelper orderHelper;

	@Override
	public OrderResponce orderByCustomer(OrderRequest orderRequest) {
		Order order=null;
		Long stocks = 0l;
		Seller seller = sellerRepository.findById(orderRequest.getSellerId())
				.orElseThrow(() -> new CustomNewException("seller id not found"));

		Product product = productRepository.findById(orderRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));

		Customer customer = customerRepository.findById(orderRequest.getCustomerId())
				.orElseThrow(() -> new CustomNewException("customer id not found"));

		SellerStocks sellerStocks = sellerStocksService.findByProductIdAndSellerId(orderRequest.getProductId(),orderRequest.getSellerId());
		if (sellerStocks == null) {
			sellerStocks = new SellerStocks();
			sellerStocks.setProduct(product);
			sellerStocks.setSeller(seller);
			//sellerStocks.setStocks(0l);
		} else {
			if (sellerStocks.getStocks() < orderRequest.getQuantity()) {
				throw new CustomNewException("quantity not available");
			}
		}
//		order = orderRepository.findByProductIdAndSellerId(orderRequest.getProductId(), orderRequest.getSellerId());
		if(orderRequest.getId()!=null&& orderRequest.getId()>0) {
			Optional<Order> optional = orderRepository.findById(orderRequest.getId());
			 if(optional.isPresent()) {
				 order=optional.get();
				 order.setQuantity(order.getQuantity() + orderRequest.getQuantity());
				 
			 }
		}
		 
		 if(order==null) {
			order = new Order();
			order.setProduct(product);
			order.setQuantity(orderRequest.getQuantity());
			order.setSeller(seller);
			order.setCustomer(customer);
		}
			stocks = sellerStocks.getStocks() != null ? sellerStocks.getStocks() : 0l;

		 sellerStocks.setStocks(stocks-orderRequest.getQuantity());
		 
		orderRepository.save(order);
		sellerStocksRepository.save(sellerStocks);

		return orderHelper.toDto(order);
	}

	@Override
	public Order findByProductIdAndSellerId(Long productId, Long sellerId) {

		return orderRepository.findByProductIdAndSellerId(productId, sellerId);
	}

	@Override
	public List<OrderResponce> getAll() {
		List<Order> orderList = orderRepository.findAll();
		
		return orderList.stream().map(orderHelper::toDto).collect(Collectors.toList());
	}

}
