package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.dto.RentDTO;
import lk.ijse.spring.dto.RentDetailsDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @Override
    public List<RentDTO> requestRentByNic(String nic) throws RuntimeException {
        return modelMapper.map(rentRepo.getRentByNic_Nic(nic),new TypeToken<ArrayList<RentDTO>>(){}.getType());
    }

    @Override
    public Long getRentsCount() throws RuntimeException {
       return rentRepo.getBookAmount();
    }

    @Override
    public void getRentRequestStatus(String rentId, String option) throws RuntimeException {
        // Retrieve the Rent object associated with the given rentId from the repository.
        Rent rent = rentRepo.findById(rentId).get();

        // Check if the option is "accepted".
        if (option.equals("accepted")) {
            // If accepted, set the status to "Accepted" and update the description with the current date and time.
            rent.setStatus("Accepted");
            rent.setDescription("Rent Accepted On : " + LocalDate.now() + " " + LocalTime.now());
        } else if (option.equals("reject")) {
            // If the option is "reject", set the status to "Rejected".
            rent.setStatus("Rejected");

            // Loop through RentDetails associated with the Rent and update driver availability status to "YES".
            for (RentDetails rentDetail : rent.getRentDetails()) {
                if (rentDetail.getDriver() != null) {
                    rentDetail.getDriver().setAvailabilityStatus("YES");
                }
            }

            // Update the description with the current date and time to indicate rejection.
            rent.setDescription("Rent Rejected On : " + LocalDate.now() + " " + LocalTime.now());
        } else {
            // If the option is not "accepted" or "reject," set the status to "Closed".
            rent.setStatus("Closed");

            // Loop through RentDetails associated with the Rent.
            for (RentDetails rentDetail : rent.getRentDetails()) {
                if (rentDetail.getDriver() != null) {
                    // If there is a driver associated, set their availability status to "YES".
                    rentDetail.getDriver().setAvailabilityStatus("YES");
                    // Also, set the car availability to "MAINTAIN".
                    rentDetail.getCar().setAvailability("MAINTAIN");
                }
            }

            // Update the description with the current date and time to indicate closure.
            rent.setDescription("Rent was Closed On : " + LocalDate.now() + " " + LocalDate.now());
        }

        // Save the updated Rent object to the repository.
        rentRepo.save(rent);
    }

    @Override
    public List<RentDetailsDTO> getDriverSchedule(String nic) throws RuntimeException {
        return modelMapper.map(rentDetailsRepo.getRentDetailsByNic(nic),new TypeToken<ArrayList<RentDetailsDTO>>(){}.getType());
    }


}
