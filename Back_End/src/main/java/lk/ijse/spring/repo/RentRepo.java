package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentRepo extends JpaRepository<Rent,String> {
    @Query(value = "SELECT rendId FROM Rent ORDER BY DESC LIMIT 1",nativeQuery = true)
    String generateLastRentId();
}
