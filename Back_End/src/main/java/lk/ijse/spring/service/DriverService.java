package lk.ijse.spring.service;

import lk.ijse.spring.dto.DriverDTO;

import java.util.List;

public interface DriverService {
    void addDriver(DriverDTO dto) throws RuntimeException;
    void updateDriver(DriverDTO dto) throws RuntimeException;
    void deleteDriver(String nic) throws RuntimeException;

    List<DriverDTO> getAllDrivers() throws RuntimeException;

    Long getAllAvailableDriversAmount() throws RuntimeException;

    Long getReservedDriversAmount() throws RuntimeException;

    DriverDTO getCurrentDriver() throws RuntimeException;
}
