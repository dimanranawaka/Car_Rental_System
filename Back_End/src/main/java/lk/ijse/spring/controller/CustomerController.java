package lk.ijse.spring.controller;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.CustomerImageDTO;
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

        return new ResponseUtil("Ok","Successfully Loaded!",customerService.getAllCustomers());
    }
    @DeleteMapping
    public ResponseUtil deleteCustomer(@RequestParam String nic){
        customerService.deleteCustomer(nic);
        return new ResponseUtil("Ok","Successfully Deleted!","");
    }
    @PutMapping
    public ResponseUtil updateCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.updateCustomer(customerDTO);
        return new ResponseUtil("Ok","Updated Successfully!","");
    }
    @GetMapping(path = "/count")
    public ResponseUtil countCustomerAmount(){
        return new ResponseUtil("Ok","Amount Calculated!",customerService.countCustomerAmount());
    }
    @GetMapping(params = {"image"})
    public ResponseUtil saveImages(@ModelAttribute CustomerImageDTO customerImageDTO){

        customerService.saveCustomerImages(customerImageDTO.getNic(),customerImageDTO);
        return new ResponseUtil("Ok","Successfully Saved!","");
    }
}
