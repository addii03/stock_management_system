package stocks_management_system.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SellerStocksRequest {

	private Long id;
	private Long stocks;
	
	private Long productId;
	
	private Long sellerId;
}
