package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;

public interface CarService {
    void addCar(CarDTO dto,CarImageDTO carImageDTO) throws RuntimeException;
}
