package stocks_management_system.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponce {
	private Long id;
	private String name;
	private String location;
	private String username;
	private String password;
	
	
//	private Long productId;
//	private String productName;
//	private String description;
//	private double price;
//	private int productQuantity;
}
