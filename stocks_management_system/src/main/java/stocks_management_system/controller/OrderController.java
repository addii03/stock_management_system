package stocks_management_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stocks_management_system.repository.OrderRepository;
import stocks_management_system.request.OrderRequest;
import stocks_management_system.responce.OrderResponce;
import stocks_management_system.servicee.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	OrderRepository orderRepository;
	
	@PostMapping("/orderByCustomer")
	public ResponseEntity<OrderResponce> orderByCustomer(@RequestBody OrderRequest orderRequest)
	{
		return new ResponseEntity<>(orderService.orderByCustomer(orderRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<OrderResponce> getAll()
	{
		return orderService.getAll();
	}
}
