package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepo extends JpaRepository<Customer,String> {
    @Query(value = "SELECT COUNT(nic) FROM Customer",nativeQuery = true)
    Long countCustomersByNic() throws RuntimeException;

    @Query(value = "SELECT * FROM Customer WHERE user_username=?", nativeQuery = true)
    Customer getCustomerByUserName(String username);
}
