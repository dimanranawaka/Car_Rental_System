package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.RentDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.entity.Rent;
import lk.ijse.spring.entity.RentDetails;
import lk.ijse.spring.repo.*;
import lk.ijse.spring.service.RentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    
    @Autowired
    ModelMapper modelMapper;
    
    @Autowired
    RentRepo rentRepo;
    
    @Autowired
    DriverRepo driverRepo;

    @Autowired
    RentDetailsRepo rentDetailsRepo;

    @Autowired
    CarRepo carRepo;

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public void rentRequest(RentDTO rentDTO) throws RuntimeException {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Rent map = modelMapper.map(rentDTO, Rent.class);

        System.out.println(map);
        if (rentDTO.getDriverRequest().equals("YES")){
            List<Driver> allAvailableDriverList = driverRepo.getAllAvailableDriverList();

            int i;
            for (RentDetails rentDetail : map.getRentDetails()) {
                // Randomly selecting a driver from above List
                i = new Random().nextInt(allAvailableDriverList.size());

                // Setting Nic for Each driver from the above List
                rentDetail.setNic(allAvailableDriverList.get(i).getNic());
                Car car = carRepo.findById(rentDetail.getRegNum()).get();
                car.setAvailability("NO");

                carRepo.save(car);

                allAvailableDriverList.get(i).setAvailabilityStatus("NO");
                // Saving Each driver from the above List
                driverRepo.save(allAvailableDriverList.get(i));

            }

        }
        rentRepo.save(map);
    }

    @Override
    public String generateNewRentId() throws RuntimeException {
        String s = rentRepo.generateLastRentId();
        return s !=null ? String.format("RID-%03d",(Integer.parseInt(s.replace("RID-",""))+1)) : "RID-001";
    }

    @Override
    public List<RentDTO> getAllRentRecords() throws RuntimeException {
       return modelMapper.map(rentRepo.findAll(),new TypeToken<ArrayList<RentDTO>>(){}.getType());
    }

    @Override
    public CustomerDTO getCustomer(String username) throws RuntimeException {
       return modelMapper.map(customerRepo.getCustomerByUserName(username),CustomerDTO.class);
    }

    @Override
    public RentDTO requestRentByRentId(String rentId) throws RuntimeException {
        return modelMapper.map(rentRepo.findById(rentId),RentDTO.class);
    }


}
