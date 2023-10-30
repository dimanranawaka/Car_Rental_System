package lk.ijse.spring.repo;

import lk.ijse.spring.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<Payment,String> {
    List<Payment> findAllByRentId_Nic_Nic(String nic) throws RuntimeException;
}
