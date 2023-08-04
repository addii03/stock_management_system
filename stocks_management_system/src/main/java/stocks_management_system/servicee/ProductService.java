package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.request.ProductRequest;
import stocks_management_system.responce.ProductResponce;

public interface ProductService {

	ProductResponce createProduct (ProductRequest productRequest);
	ProductResponce getById(long id);
	List<ProductResponce> getAll();
}
