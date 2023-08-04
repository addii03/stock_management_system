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

import stocks_management_system.repository.SellerStocksRepository;
import stocks_management_system.request.SellerStocksRequest;
import stocks_management_system.responce.SellerStocksResponce;
import stocks_management_system.servicee.SellerStocksService;

@RestController
@RequestMapping("/api/sellerStock")
public class SellerStockController {

	@Autowired
	SellerStocksService sellerStocksService;
	@Autowired
	SellerStocksRepository sellerStocksRepository;
	
	@PostMapping("/allocateStock")
	public ResponseEntity<SellerStocksResponce>allocateStock(@RequestBody SellerStocksRequest sellerStocksRequest)
	{
		return new ResponseEntity<>(sellerStocksService.allocateStock(sellerStocksRequest), HttpStatus.CREATED);
	}
	@GetMapping("/getAll")
	public List<SellerStocksResponce> getAll()
	{
		return sellerStocksService.getAll();
	}
}
