package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;

public interface DriverService {
    void addDriver(DriverDTO dto) throws RuntimeException;
    void updateDriver(DriverDTO dto) throws RuntimeException;
}
