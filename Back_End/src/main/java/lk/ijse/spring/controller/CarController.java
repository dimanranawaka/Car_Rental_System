package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarAllDTO;
import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;
import lk.ijse.spring.embeddable.FreeMileage;
import lk.ijse.spring.embeddable.Price;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseUtil addCar(@ModelAttribute CarImageDTO carImageDTO, @ModelAttribute Price price, @ModelAttribute FreeMileage freeMileage, @ModelAttribute CarDTO carDTO){

        carDTO.setCarImageDTO(carImageDTO);
        carDTO.setPrice(price);
        carDTO.setFreeMileage(freeMileage);

        service.addCar(carDTO);

        return new ResponseUtil("Ok","Car added Successfully!","");
    }

    @GetMapping
    public ResponseUtil getAllCars(){
        List<Car> allCars = service.getAllCars();
        return new ResponseUtil("Ok","Successfully Loaded!",allCars);
    }

    @GetMapping(params={"regNum"})
    public ResponseUtil getCar(@RequestParam String regNum){

        CarAllDTO car = service.getCar(regNum);
        return new ResponseUtil("Ok","Successfully Loaded!",car);

    }
    @GetMapping(path = "/count")
    public ResponseUtil countAvailableCar(){
        Long l = service.countAvailableCar();
        return new ResponseUtil("Ok","Successfully Counted!",l);
    }

    @GetMapping(path = "/count/reserved")
    public ResponseUtil countReservedCarAmount(){
        return new ResponseUtil("Ok","Successfully Counted!","");
    }
}
