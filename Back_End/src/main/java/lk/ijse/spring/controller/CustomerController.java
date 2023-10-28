package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Transactional
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping
    public ResponseUtil addCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.addCustomer(customerDTO);
        return new ResponseUtil("Ok","Successfully Added!","");
    }
    @GetMapping
    public ResponseUtil getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseUtil("Ok","Successfully Loaded!",allCustomers);
    }
    @DeleteMapping
    public ResponseUtil deleteCustomer(@RequestParam String nic){
        return new ResponseUtil("","","");
    }
}
