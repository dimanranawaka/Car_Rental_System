package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    @Override
    public void addCar(CarDTO dto) {

    }
}
