package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional // AOP //
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    public CustomerServiceImpl() {
        System.out.println("CustomerServiceImpl : Instantiated");
    }

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

}
