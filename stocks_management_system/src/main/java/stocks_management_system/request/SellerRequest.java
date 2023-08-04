package stocks_management_system.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequest {
	
	
	private Long id;
	private String name;
	private String location;
	private String username;
	private String password;
	//private Long productId;
}
