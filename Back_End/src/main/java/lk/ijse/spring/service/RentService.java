package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerImageDTO;
import lk.ijse.spring.dto.RentDTO;

import java.util.List;

public interface RentService {
    void rentRequest(RentDTO rentDTO) throws RuntimeException;
    String generateNewRentId() throws RuntimeException;
    List<RentDTO> getAllRentRecords() throws RuntimeException;

    CustomerImageDTO getCustomer(String username) throws RuntimeException;
}
