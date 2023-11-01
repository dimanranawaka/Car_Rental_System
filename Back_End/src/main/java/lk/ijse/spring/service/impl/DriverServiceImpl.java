package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverAllStringDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ImagePathWriterUtil;
import lk.ijse.spring.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DriverRepo driverRepo;

    @Override
    public void addDriver(DriverDTO driverDTO) throws RuntimeException{

        if (driverRepo.existsById(driverDTO.getNic())){
            throw new RuntimeException("Driver Already Exists!");
        }
        Driver driver = modelMapper.map(driverDTO, Driver.class);

//        dto.getUser().setRole("Driver");
//        map.setUser(dto.getUser());

        try {

           if (driverDTO.getLicenseImage().getBytes()!=null){

               byte[] bytes = driverDTO.getLicenseImage().getBytes();

               Path licenseImageLocation = Paths.get(ImagePathWriterUtil.projectPath,"/images/bucket/driver/license_",driver.getNic()+".jpeg");
               Files.write(licenseImageLocation,bytes);
               driverDTO.getLicenseImage().transferTo(licenseImageLocation);

               driver.setLicenseImage("/images/bucket/driver/license_"+driver.getNic()+".jpeg");
           }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.setAvailabilityStatus("YES");
        driver.getUser().setRole("Driver");

        driverRepo.save(driver);
    }

    @Override
    public void updateDriver(DriverDTO driverDTO) throws RuntimeException {

        if (!driverRepo.existsById(driverDTO.getNic())){
            throw new RuntimeException(driverDTO.getNic()+" : is not Exists!");
        }

        Driver driver = modelMapper.map(driverDTO, Driver.class);

        Driver driver1 = driverRepo.findById(driverDTO.getNic()).get();

        driver.setLicenseImage(driver1.getLicenseImage());

        driver.setAvailabilityStatus("YES");
        driver.getUser().setRole("Driver");
        driverRepo.save(driver);

    }

    @Override
    public void deleteDriver(String nic) throws RuntimeException {
        if (!driverRepo.existsById(nic)){
            throw new RuntimeException(nic +" : is not Exists!");
        }
        driverRepo.deleteById(nic);
    }

    @Override
    public List<DriverDTO> getAllDrivers() throws RuntimeException {
       return modelMapper.map(driverRepo.findAll(),new TypeToken<ArrayList<DriverAllStringDTO>>(){}.getType());
    }

    @Override
    public Long getAllAvailableDriversAmount() throws RuntimeException {
        return driverRepo.countAvailableDriversAmount();
    }

    @Override
    public Long getReservedDriversAmount() throws RuntimeException {
        return driverRepo.countReservedDriversAmount();
    }

    @Override
    public DriverDTO getCurrentDriver() throws RuntimeException {
        return modelMapper.map(driverRepo.getDriverByUserName(UserUtil.currentUser.getUsername()),DriverDTO.class);
    }
}
