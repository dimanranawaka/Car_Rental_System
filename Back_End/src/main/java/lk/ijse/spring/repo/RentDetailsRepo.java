package lk.ijse.spring.repo;

import lk.ijse.spring.entity.RentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentDetailsRepo extends JpaRepository<RentDetails,String> {
    public List<RentDetails> getRentDetailsByNic(String nic);

    public void deleteRentDetailsByRegNum(String rentId);
}
