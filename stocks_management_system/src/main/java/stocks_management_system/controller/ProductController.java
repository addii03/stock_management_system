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

import stocks_management_system.helper.ProductHelper;
import stocks_management_system.request.ProductRequest;
import stocks_management_system.responce.ProductResponce;
import stocks_management_system.servicee.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	ProductHelper productHelper;

	@PostMapping("/create")
	public ResponseEntity<ProductResponce> createProduct(@RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);

	}
	@GetMapping("/getAll")
	public List<ProductResponce>  getAll()
	{
		return productService.getAll();
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<ProductResponce> getById(long id)
	{
		return new ResponseEntity<>(productService.getById(id),HttpStatus.OK);
	}
}
