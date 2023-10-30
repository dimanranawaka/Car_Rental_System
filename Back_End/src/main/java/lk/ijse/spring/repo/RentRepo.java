package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RentRepo extends JpaRepository<Rent,String> {
    @Query(value = "SELECT rendId FROM Rent ORDER BY DESC LIMIT 1",nativeQuery = true)
    String generateLastRentId();
    List<Rent> getRentByNic_Nic() throws RuntimeException;

    @Query(value = "SELECT COUNT(rentId) FROM Rent WHERE status!='Closed'",nativeQuery = true)
    Long getBookAmount() throws RuntimeException;
}
