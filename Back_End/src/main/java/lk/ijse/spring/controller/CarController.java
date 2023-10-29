package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;
import lk.ijse.spring.embeddable.FreeMileage;
import lk.ijse.spring.embeddable.Price;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
