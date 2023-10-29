package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.RentDetailsRepo;
import lk.ijse.spring.service.CarService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Autowired
    CarRepo carRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RentDetailsRepo detailsRepo;

    @Override
    public void addCar(CarDTO dto) {

    }
}
