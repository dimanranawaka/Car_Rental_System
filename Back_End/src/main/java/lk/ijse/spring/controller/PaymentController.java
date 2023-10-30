package lk.ijse.spring.controller;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.service.PaymentService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping
    public ResponseUtil addPayment(@RequestBody PaymentDTO dto){
        service.addPayment(dto);
        return new ResponseUtil("Ok","Successfully added!","");
    }
}
