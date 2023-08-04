package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import stocks_management_system.entity.Product;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.ProductHelper;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.request.ProductRequest;
import stocks_management_system.responce.ProductResponce;
import stocks_management_system.servicee.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductHelper productHelper;
	@Autowired
	ProductRepository productRepository;
	@Override
	public ProductResponce createProduct(ProductRequest productRequest) {
		Product product = productHelper.toEntity(productRequest);
	productRepository.save(product);
	return productHelper.toDto(product);
	
	}
	@Override
	public ProductResponce getById(long id) {
		Product product = productRepository.findById(id).orElseThrow(()-> new CustomNewException("id not found"));
		return productHelper.toDto(product);
	
	}
	@Override
	public List<ProductResponce> getAll() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().map(productHelper::toDto).collect(Collectors.toList());
		
	}
	
}
