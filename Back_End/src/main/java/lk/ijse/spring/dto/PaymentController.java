package lk.ijse.spring.dto;

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
        return new ResponseUtil("","","");
    }
}
