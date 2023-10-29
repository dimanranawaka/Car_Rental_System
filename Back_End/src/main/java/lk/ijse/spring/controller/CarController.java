package lk.ijse.spring.controller;

import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CarController {

    @Autowired
    CarService service;

    @PostMapping
    public ResponseUtil addCar(){
        return new ResponseUtil("","","");
    }
}
