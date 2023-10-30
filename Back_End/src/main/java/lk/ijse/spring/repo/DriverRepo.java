package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverRepo extends JpaRepository<Driver,String> {
    @Query(value = "SELECT * FROM Driver WHERE availabilityStatus='YES'",nativeQuery = true)
    List<Driver> getAllAvailableDriverList() throws RuntimeException;

    @Query(value = "SELECT * FROM Driver WHERE user_username=?",nativeQuery = true)
    Driver getDriverByUserName(String username);

    @Query(value = "SELECT COUNT(nic) FROM Driver WHERE availabilityStatus='YES'",nativeQuery = true)
    Long countAvailableDriverAmount() throws RuntimeException;
}
