package stocks_management_system.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponce {
	private Long id;
	private String name;
	private String address;
	private String username;
	private String password;
}
