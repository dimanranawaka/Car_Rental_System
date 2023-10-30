package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@CrossOrigin
public class DriverController {
    @Autowired
    DriverService service;

    @PostMapping
    public ResponseUtil addDriver(@RequestParam String username, @RequestParam String password, @ModelAttribute DriverDTO dto){
        return new ResponseUtil("","","");
    }

}
