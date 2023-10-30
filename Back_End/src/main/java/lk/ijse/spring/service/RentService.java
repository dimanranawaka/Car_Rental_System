package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.RentDTO;
import lk.ijse.spring.dto.RentDetailsDTO;

import java.util.List;

public interface RentService {
    void rentRequest(RentDTO rentDTO) throws RuntimeException;
    String generateNewRentId() throws RuntimeException;
    List<RentDTO> getAllRentRecords() throws RuntimeException;

    CustomerDTO getCustomer(String username) throws RuntimeException;

    RentDTO requestRentByRentId(String rentId) throws RuntimeException;

    List<RentDTO> requestRentByNic(String nic) throws RuntimeException;

    Long getRentsCount() throws RuntimeException;

    void getRentRequestStatus(String rentId, String option) throws RuntimeException;

    List<RentDetailsDTO> getDriverSchedule(String nic) throws RuntimeException;
}
