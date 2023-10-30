package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverAllStringDTO;
import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.entity.Driver;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import lk.ijse.spring.util.ImagePathWriterUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
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
    public void addDriver(DriverDTO dto) throws RuntimeException{
        if (driverRepo.existsById(dto.getNic())){
            throw new RuntimeException("Driver Already Exists!");
        }
        Driver map = modelMapper.map(dto, Driver.class);

//        dto.getUser().setRole("Driver");
//        map.setUser(dto.getUser());

        try {

           if (dto.getLicenseImage().getBytes()!=null){
               byte[] bytes = dto.getLicenseImage().getBytes();

               Path licenseImageLocation = Paths.get(ImagePathWriterUtil.projectPath,"/images/driver/license_",map.getLicense()+".jpeg");
               Files.write(licenseImageLocation,bytes);
               map.setLicenseImage("images/driver/license_"+map.getLicense()+".jpeg");
           }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        map.setAvailabilityStatus("YES");
        driverRepo.save(map);
    }

    @Override
    public void updateDriver(DriverDTO dto) throws RuntimeException {

        if (!driverRepo.existsById(dto.getNic())){
            throw new RuntimeException(dto.getNic()+" : is not Exists!");
        }

        Driver map = modelMapper.map(dto, Driver.class);

        Driver driver = driverRepo.findById(dto.getNic()).get();

        map.setLicenseImage(driver.getLicenseImage());

        map.setAvailabilityStatus("YES");
        map.getUser().setRole("Driver");
        driverRepo.save(map);
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
}
