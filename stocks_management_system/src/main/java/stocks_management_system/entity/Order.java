package stocks_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long quantity;
	
	@ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id", nullable = false)
	Product product;

	@ManyToOne(targetEntity = Seller.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id", nullable = false)
	Seller seller;
	
	@ManyToOne(targetEntity =  Customer.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	Customer customer;
}
