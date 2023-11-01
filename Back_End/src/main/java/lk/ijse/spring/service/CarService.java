package lk.ijse.spring.service;

import lk.ijse.spring.dto.CarAllDTO;
import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;

import java.util.List;

public interface CarService {
    void addCar(CarDTO carDTO) throws RuntimeException;

    List<CarDTO> getAllCars() throws RuntimeException;

    public CarAllDTO getCar(String regNum)throws RuntimeException;

    Long countAvailableCar()throws RuntimeException;

    Long countReserveCarAmount() throws RuntimeException;

    void updateCar(CarDTO carDTO) throws RuntimeException;

    void deleteCar(String regNum) throws RuntimeException;

    Long countMaintainingCarAmount() throws RuntimeException;

    List countCarAmountByBrand() throws RuntimeException;

    void editCarImages(CarImageDTO carImageDTO) throws RuntimeException;

    List<CarAllDTO> filterCarsByRegNum(String text,String search,String fuel) throws RuntimeException;

    void moveCarToMaintain(String regNum) throws RuntimeException;
}
