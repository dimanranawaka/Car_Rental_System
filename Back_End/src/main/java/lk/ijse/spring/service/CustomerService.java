package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO dto);

     List<CustomerDTO> getAllCustomers();

     void deleteCustomer(String nic);

     void updateCustomer(CustomerDTO customerDTO);
}
