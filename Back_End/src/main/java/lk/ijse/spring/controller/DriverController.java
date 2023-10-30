package lk.ijse.spring.controller;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.dto.UserDTO;
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
        dto.setUser(new UserDTO(username,password,"Driver"));
        service.addDriver(dto);
        return new ResponseUtil("Ok","Driver Added Successful!","");
    }

    @PostMapping(path = "/update")
    public ResponseUtil updateDriver(@RequestParam String username,@RequestParam String password,@ModelAttribute DriverDTO dto){
        dto.setUser(new UserDTO(username,password,"Driver"));
        service.updateDriver(dto);
        return new ResponseUtil("Ok","Successfully Updated!","");
    }
    @DeleteMapping
    public ResponseUtil deleteDriver(String nic){
        service.deleteDriver(nic);
        return new ResponseUtil("Ok","Successfully Deleted!","");
    }
    @GetMapping(path = "/all")
    public ResponseUtil getAllDrivers(){
        return new ResponseUtil("Ok","Successfully Loaded!",service.getAllDrivers());
    }

    @GetMapping(path = "/available")
    public ResponseUtil getAvailableDriversAmount(){
        return new ResponseUtil("Ok","Successfully Loaded!",service.getAllAvailableDriversAmount());
    }
    @GetMapping(path = "/reserved")
    public ResponseUtil getReservedDriversAmount(){
        return new ResponseUtil("Ok","Successfully Loaded!",service.getReservedDriversAmount());
    }
    @GetMapping()
    public ResponseUtil getCurrentDriver(){
        return new ResponseUtil("Ok","Successfully Loaded!",service.getCurrentDriver());
    }
}
