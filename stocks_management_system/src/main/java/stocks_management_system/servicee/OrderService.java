package stocks_management_system.servicee;

import java.util.List;

import stocks_management_system.entity.Order;
import stocks_management_system.request.OrderRequest;
import stocks_management_system.responce.OrderResponce;

public interface OrderService {

	OrderResponce orderByCustomer(OrderRequest orderRequest);

	Order findByProductIdAndSellerId(Long productId, Long sellerId);

	List<OrderResponce>getAll();
}
