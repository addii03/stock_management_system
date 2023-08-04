package stocks_management_system.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProductRequest {
	private Long id;
	private Long stockQuantity;
	
	private Long userId;
	private Long productId;
}
