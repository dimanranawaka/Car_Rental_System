package lk.ijse.spring.controller;

import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {
    @Autowired
    DriverService service;
    @Autowired
    RentService rentService;
}
