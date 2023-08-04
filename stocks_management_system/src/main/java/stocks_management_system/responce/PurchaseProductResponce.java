package stocks_management_system.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProductResponce {

	private Long id;	
	private Long stockQuantity;

	//product
	private Long productId;
	private String name;
	private String description;
	private double sellingPrice;
	private double purchasePrice;
	
	//user
	private Long userId;
	private String userEntityName;
	private String username;
	private String email;
	private String address;
	private String password;
}
