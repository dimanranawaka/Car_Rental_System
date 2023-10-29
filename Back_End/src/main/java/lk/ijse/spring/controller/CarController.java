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
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseUtil addCar(@ModelAttribute CarImageDTO carImageDTO, @ModelAttribute Price price,
    @ModelAttribute FreeMileage freeMileage, @ModelAttribute CarDTO carDTO){

        carDTO.setCarImageDTO(carImageDTO);
        carDTO.setPrice(price);
        carDTO.setFreeMileage(freeMileage);

        service.addCar(carDTO);

        return new ResponseUtil("Ok","Car added Successfully!","");
    }

    @PostMapping(path = "/image")
    public ResponseUtil editCarImages(@ModelAttribute CarImageDTO dto,@ModelAttribute CarDTO carDTO){
        service.editCarImages(dto,carDTO);
        return new ResponseUtil("Ok","Successfully Modified!","");
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

    @PostMapping("/update")
    public ResponseUtil updateCar(@ModelAttribute CarImageDTO carImageDTO,
      @ModelAttribute Price price,@ModelAttribute FreeMileage freeMileage,@ModelAttribute CarDTO carDTO){

        carDTO.setCarImageDTO(carImageDTO);
        carDTO.setPrice(price);
        carDTO.setFreeMileage(freeMileage);

        service.updateCar(carDTO);

        return new ResponseUtil("Ok","Successfully Updated","");
    }
    @DeleteMapping
    public ResponseUtil deleteCar(@RequestParam String regNum){
        service.deleteCar(regNum);
        return new ResponseUtil("Ok","Successfully Deleted!","");
    }

    // Counting and Filtering
    @GetMapping(path = "/count")
    public ResponseUtil countAvailableCar(){
        Long l = service.countAvailableCar();
        return new ResponseUtil("Ok","Successfully Counted!",l);
    }

    @GetMapping(path = "/count/reserved")
    public ResponseUtil countReservedCarAmount(){
        return new ResponseUtil("Ok","Successfully Counted!",service.countReserveCarAmount());
    }

    @GetMapping("/count/maintain")
    public ResponseUtil countMaintainingCarAmount(){

        return new ResponseUtil("Ok","Successfully Counted!",service.countMaintainingCarAmount());
    }
    @GetMapping(path = "/brand")
    public ResponseUtil countCarAmountByBrand(){
        return new ResponseUtil("","",service.countCarAmountByBrand());
    }
    @GetMapping(path = "/filterByRegNum")
    public  ResponseUtil filterByNum(@RequestParam String text, @RequestParam String search ,@RequestParam String fuel){
        return new ResponseUtil("","","");
    }

}
