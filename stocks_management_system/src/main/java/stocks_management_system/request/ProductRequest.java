package stocks_management_system.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private Long id;
	private String name;
	private String description;
	private double sellingPrice;
	private double purchasePrice;
	
	//List<Long> sellerIds;
}
