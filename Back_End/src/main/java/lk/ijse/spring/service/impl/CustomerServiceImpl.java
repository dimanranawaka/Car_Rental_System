package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.CustomerImageDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import lk.ijse.spring.util.ImagePathWriterUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@Transactional // AOP //
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;



    @Override
    public void addCustomer(CustomerDTO customerDTO) {
        if(customerRepo.existsById(customerDTO.getNic())){
            throw new RuntimeException(customerDTO.getNic()+" : is Already Exists!");
        }
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepo.save(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> allCustomers = customerRepo.findAll();
        return modelMapper.map(allCustomers,new TypeToken<List<CustomerDTO>>(){}.getType());
    }

    @Override
    public void deleteCustomer(String nic) {
       if (!customerRepo.existsById(nic)){
           throw new RuntimeException(nic+" is not Exists to deleted!");
       }
       customerRepo.deleteById(nic);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO){
        if (!customerRepo.existsById(customerDTO.getNic())){
            throw new RuntimeException(customerDTO.getNic()+" : is not Exists!");}

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer updatedCustomer = customerRepo.findById(customerDTO.getNic()).get();

        customer.setNicImage(updatedCustomer.getNicImage());
        customer.setLicenseImage(updatedCustomer.getLicenseImage());
        customer.getUser().setRole("Customer");

        customerRepo.save(customer);
    }

    @Override
    public Long countCustomerAmount() {
        return customerRepo.countCustomersByNic();
    }

    @Override
    public void saveCustomerImages(String nic,CustomerImageDTO customerImageDTO) throws RuntimeException {
        Customer customer = customerRepo.findById(nic).get();

        if (customerImageDTO.getLicenseImage() !=null && customerImageDTO.getNicImage() !=null){
            try {

                byte[] nicB = customerImageDTO.getNicImage().getBytes();
                byte[] licenseB = customerImageDTO.getLicenseImage().getBytes();

                String projectPath = "D:\\Dev\\Java\\My_Projects\\Car_Rental_System\\Front_End\\assets";

                Path nicPath = Paths.get(projectPath + "/images/bucket/customer/nic/nic_" + nic + ".jpeg");
                Path licPath = Paths.get(projectPath + "/images/bucket/customer/lic/license_" +  nic + ".jpeg");

                Files.write(nicPath,nicB);
                Files.write(licPath,licenseB);

                customerImageDTO.getNicImage().transferTo(nicPath);
                customerImageDTO.getLicenseImage().transferTo(licPath);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            customer.setLicenseImage("/assets/images/bucket/customer/license/license_"+".jpeg");
            customer.setNicImage("/assets/images/bucket/customer/nic/nic_"+".jpeg");

            customer.getUser().setRole("Customer");
            customerRepo.save(customer);
        }
    }

}
