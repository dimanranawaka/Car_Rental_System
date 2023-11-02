package lk.ijse.spring.controller;


import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;
import lk.ijse.spring.embeddable.FreeMileage;
import lk.ijse.spring.embeddable.Price;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin
public class CarController {

    @Autowired
    CarService carService;

    @PostMapping
    public ResponseUtil addCar(@ModelAttribute CarImageDTO carImageDTO, @ModelAttribute Price price, @ModelAttribute FreeMileage freeMileage, @ModelAttribute CarDTO carDTO){

        carDTO.setCarImageDTO(carImageDTO);
        carDTO.setPrice(price);
        carDTO.setFreeMileage(freeMileage);

        carService.addCar(carDTO);

        return new ResponseUtil("Ok","Car added Successfully!","");
    }

    @PostMapping(path = "/image")
    public ResponseUtil editCarImages(@ModelAttribute CarImageDTO carImageDTO){
        carService.editCarImages(carImageDTO);
        return new ResponseUtil("Ok","Successfully Modified!","");
    }

    @GetMapping
    public ResponseUtil getAllCars(){
        return new ResponseUtil("Ok","Successfully Loaded!", carService.getAllCars());
    }

    @GetMapping(params={"regNum"})
    public ResponseUtil getCar(@RequestParam String regNum){
        return new ResponseUtil("Ok","Successfully Loaded!", carService.getCar(regNum));

    }

    @PostMapping("/update")
    public ResponseUtil updateCar(@ModelAttribute CarImageDTO carImageDTO, @ModelAttribute Price price,@ModelAttribute FreeMileage freeMileage,@ModelAttribute CarDTO carDTO){

        carDTO.setCarImageDTO(carImageDTO);
        carDTO.setPrice(price);
        carDTO.setFreeMileage(freeMileage);

        carService.updateCar(carDTO);

        return new ResponseUtil("Ok","Successfully Updated","");
    }
    @DeleteMapping
    public ResponseUtil deleteCar(@RequestParam String regNum){
        carService.deleteCar(regNum);
        return new ResponseUtil("Ok","Successfully Deleted!","");
    }

    // Counting and Filtering
    @GetMapping(path = "/count")
    public ResponseUtil countAvailableCar(){
        Long l = carService.countAvailableCar();
        return new ResponseUtil("Ok","Successfully Counted!",l);
    }

    @GetMapping(path = "/count/reserved")
    public ResponseUtil countReservedCarAmount(){
        return new ResponseUtil("Ok","Successfully Counted!", carService.countReserveCarAmount());
    }

    @GetMapping("/count/maintain")
    public ResponseUtil countMaintainingCarAmount(){

        return new ResponseUtil("Ok","Successfully Counted!", carService.countMaintainingCarAmount());
    }
    @GetMapping(path = "/brand")
    public ResponseUtil countCarAmountByBrand(){
        return new ResponseUtil("","", carService.countCarAmountByBrand());
    }
    @GetMapping(path = "/filterByRegNum")
    public  ResponseUtil filterByNum(@RequestParam String text, @RequestParam String search ,@RequestParam String fuel){
        return new ResponseUtil("","", carService.filterCarsByRegNum(text,search,fuel));
    }

    @PutMapping(params = {"regNum"})
    public ResponseUtil moveCarToMaintain(@RequestParam String regNum){
        carService.moveCarToMaintain(regNum);
        return new ResponseUtil("OK","STATUS UPDATED!","");
    }
}
