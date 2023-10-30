package lk.ijse.spring.service;

import lk.ijse.spring.dto.RentDTO;

public interface RentService {
    void rentRequest(RentDTO rentDTO) throws RuntimeException;
}
