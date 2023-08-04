package stocks_management_system.responce;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponce {
	private Long id;
	private String name;
	private String username;
	private String email;
	private String address;
	private String password;
}
