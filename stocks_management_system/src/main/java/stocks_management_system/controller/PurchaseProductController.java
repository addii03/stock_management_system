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

import stocks_management_system.helper.PurchaseProductHelper;
import stocks_management_system.request.PurchaseProductRequest;
import stocks_management_system.responce.PurchaseProductResponce;
import stocks_management_system.servicee.PurchaseProductService;

@RestController
@RequestMapping("/api/purchaseProduct")
public class PurchaseProductController {
	@Autowired
	PurchaseProductService purchaseProductService;
	@Autowired
	PurchaseProductHelper purchaseProductHelper;
	
	@PostMapping("/create")
	public ResponseEntity<PurchaseProductResponce> purchaseProduct(@RequestBody PurchaseProductRequest purchaseProductRequest)
	{
		return new ResponseEntity<>(purchaseProductService.purchaseProduct(purchaseProductRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public List<PurchaseProductResponce>getAll() 
	{
		return purchaseProductService.getAll();
	}
}
