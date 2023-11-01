package lk.ijse.spring.service;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.CustomerImageDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO dto) throws RuntimeException;

     List<CustomerDTO> getAllCustomers() throws RuntimeException;

     void deleteCustomer(String nic) throws RuntimeException;

     void updateCustomer(CustomerDTO customerDTO) throws RuntimeException;
     Long countCustomerAmount() throws  RuntimeException;

     void saveCustomerImages(String nic, CustomerImageDTO customerImageDTO) throws RuntimeException;
}
