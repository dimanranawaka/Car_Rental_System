package lk.ijse.spring.controller;

import lk.ijse.spring.dto.RentDTO;
import lk.ijse.spring.service.RentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rent")
@CrossOrigin
public class RentController {
    @Autowired
    RentService rentService;

    @PostMapping
    public ResponseUtil rentRequest(@RequestBody RentDTO rentDTO){
        rentService.rentRequest(rentDTO);
        return new ResponseUtil("Ok","Request Successfully","");
    }

    @GetMapping
    public ResponseUtil generateNewRentId(){
        return new ResponseUtil("Ok","Generated Successfully",rentService.generateNewRentId());
    }
    @GetMapping(path = "/all")
    public ResponseUtil getAllRentRecords(){
        return new ResponseUtil("Ok","Successfully Loaded!",rentService.getAllRentRecords());
    }
    @GetMapping(params = {"username"})
    public ResponseUtil getCustomer(@RequestParam String username){
        return new ResponseUtil("Ok","Successfully Loaded!",rentService.getCustomer(username));
    }
    @GetMapping(params = {"rentID"})
    public ResponseUtil getRentByRentId(@RequestParam String rentId){
        return new ResponseUtil("","","");
    }
}
