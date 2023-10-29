package lk.ijse.spring.controller;

import lk.ijse.spring.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CarController {

    @PostMapping
    public ResponseUtil addCar(){
        return new ResponseUtil("","","");
    }
}
