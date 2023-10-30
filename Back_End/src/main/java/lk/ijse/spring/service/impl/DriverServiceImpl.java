package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.DriverDTO;
import lk.ijse.spring.repo.DriverRepo;
import lk.ijse.spring.service.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DriverRepo driverRepo;

    @Override
    public void addDriver(DriverDTO dto) {

    }
}
