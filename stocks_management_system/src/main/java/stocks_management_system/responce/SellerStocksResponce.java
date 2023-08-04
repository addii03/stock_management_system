package stocks_management_system.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SellerStocksResponce {

	private Long id;
	private Long stocks;
	
	private Long productId;	
	private String productName;
	private String description;
	private double sellingPrice;
	private double purchasePrice;
	
	private Long sellerId;
	private String name;
	private String location;
	private String username;
	private String password;
}
