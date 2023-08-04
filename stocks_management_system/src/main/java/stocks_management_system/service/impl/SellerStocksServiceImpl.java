package stocks_management_system.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import stocks_management_system.entity.Product;
import stocks_management_system.entity.PurchaseProduct;
import stocks_management_system.entity.Seller;
import stocks_management_system.entity.SellerStocks;
import stocks_management_system.exception.CustomNewException;
import stocks_management_system.helper.SellerStocksHelper;
import stocks_management_system.repository.ProductRepository;
import stocks_management_system.repository.PurchaseProductRepository;
import stocks_management_system.repository.SellerRepository;
import stocks_management_system.repository.SellerStocksRepository;
import stocks_management_system.request.SellerStocksRequest;
import stocks_management_system.responce.SellerStocksResponce;
import stocks_management_system.servicee.PurchaseProductService;
import stocks_management_system.servicee.SellerStocksService;

@Service
public class SellerStocksServiceImpl implements SellerStocksService {

	@Autowired
	SellerStocksRepository sellerStocksRepository;

	@Autowired
	SellerStocksHelper sellerStocksHelper;

	@Autowired
	SellerRepository sellerRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	PurchaseProductRepository purchaseProductRepository;
	@Autowired
	PurchaseProductService purchaseProductService;

	@SuppressWarnings("null")
	@Override
	public SellerStocksResponce allocateStock(SellerStocksRequest sellerStocksRequest) {
		SellerStocks sellerStocks;
		Long stockQuantity = 0l;
		Seller seller = sellerRepository.findById(sellerStocksRequest.getSellerId())
				.orElseThrow(() -> new CustomNewException("seller id not found"));

		Product product = productRepository.findById(sellerStocksRequest.getProductId())
				.orElseThrow(() -> new CustomNewException("product id not found"));

		PurchaseProduct purchaseProduct = purchaseProductService
				.findByProductId(sellerStocksRequest.getProductId());
		if(purchaseProduct!=null) {
			if (purchaseProduct.getStockQuantity() < sellerStocksRequest.getStocks()) {
				throw new CustomNewException("quantity not available");
			}
		}
		else {
			purchaseProduct= new PurchaseProduct();
		}
		sellerStocks = sellerStocksRepository.findByProductIdAndSellerId(sellerStocksRequest.getProductId(),
				sellerStocksRequest.getSellerId());
		if (sellerStocks == null) {

			sellerStocks = new SellerStocks();
			sellerStocks.setProduct(product);
			sellerStocks.setStocks(sellerStocksRequest.getStocks());
			sellerStocks.setSeller(seller);
			stockQuantity = purchaseProduct.getStockQuantity()!=null?purchaseProduct.getStockQuantity():0l;
		} else {
			stockQuantity = purchaseProduct.getStockQuantity()!=null?purchaseProduct.getStockQuantity():0l;
			sellerStocks.setStocks(sellerStocks.getStocks() + sellerStocksRequest.getStocks());

		}
		purchaseProduct.setStockQuantity(stockQuantity - sellerStocksRequest.getStocks());
		sellerStocksRepository.save(sellerStocks);
		purchaseProductRepository.save(purchaseProduct);
		return sellerStocksHelper.toDto(sellerStocks);
	}

	@Override
	public SellerStocks findByProductIdAndSellerId(Long productId, Long sellerId) {
		return 	sellerStocksRepository.findByProductIdAndSellerId(productId,sellerId);
	}

	@Override
	public SellerStocks findByProductId(Long productId) {
		
		return sellerStocksRepository.findByProductId(productId);
	}

	@Override
	public List<SellerStocksResponce> getAll() {
		List<SellerStocks> sellerStockList = sellerStocksRepository.findAll();
		return sellerStockList.stream().map(sellerStocksHelper::toDto).collect(Collectors.toList());
	}

}
