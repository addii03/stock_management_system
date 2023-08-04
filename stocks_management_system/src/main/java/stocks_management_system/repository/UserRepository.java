package stocks_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stocks_management_system.entity.Customer;
import stocks_management_system.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByUsername(String username);
}
