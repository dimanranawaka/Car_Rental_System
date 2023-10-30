package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.RentDTO;

import java.util.List;

public interface RentService {
    void rentRequest(RentDTO rentDTO) throws RuntimeException;
    String generateNewRentId() throws RuntimeException;
    List<RentDTO> getAllRentRecords() throws RuntimeException;

    CustomerDTO getCustomer(String username) throws RuntimeException;

    RentDTO requestRentByRentId(String rentId) throws RuntimeException;
}
