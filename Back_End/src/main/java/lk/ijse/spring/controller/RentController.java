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
        return new ResponseUtil("","",rentService.generateNewRentId());
    }
}
