package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;
import lk.ijse.spring.entity.Car;

import java.util.List;

public interface CarService {
    void addCar(CarDTO dto) throws RuntimeException;

    List<Car> getAllCars() throws RuntimeException;
}
