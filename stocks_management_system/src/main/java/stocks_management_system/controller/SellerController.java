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

import stocks_management_system.repository.SellerRepository;
import stocks_management_system.request.SellerRequest;
import stocks_management_system.responce.SellerResponce;
import stocks_management_system.servicee.SellerService;

@RestController
@RequestMapping("/api/seller")
public class SellerController {
	@Autowired
	SellerService sellerService;
	@Autowired
	SellerRepository sellerRepository;
	
	@PostMapping("/create")
	public String createSeller(@RequestBody SellerRequest sellerRequest)
	{
		return sellerService.createSeller(sellerRequest);
	}
	@GetMapping("/gellAllSellers")
	public List<SellerResponce> getAllSeller()
	{
		return sellerService.getAllSeller();
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<SellerResponce> getById(long id)
	{
		return new ResponseEntity<>(sellerService.getById(id), HttpStatus.OK);
	}
	@PostMapping("/authenticateSeller")
	public String authenticateSeller(@RequestBody SellerRequest sellerRequest)
	{
		return sellerService.authenticateSeller(sellerRequest);
	}
	
}
