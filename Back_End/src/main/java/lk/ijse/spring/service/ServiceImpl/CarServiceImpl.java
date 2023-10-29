package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.CarAllDTO;
import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.RentDetailsRepo;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ImagePathWriterUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    public void addCar(CarDTO dto) throws RuntimeException{

        if (carRepo.existsById(dto.getRegNum())){
            throw new RuntimeException(dto.getRegNum()+" : is Already Exists!");
        }

        Car car = modelMapper.map(dto, Car.class);

        try {

            car.getCarImage().setFront(new ImagePathWriterUtil().imagePathWriter(dto.getCarImageDTO().getFront(), Paths.get(ImagePathWriterUtil.projectPath+"/images/car/front_"+dto.getRegNum()+".jpeg")));
            car.getCarImage().setBack(new ImagePathWriterUtil().imagePathWriter(dto.getCarImageDTO().getBack(), Paths.get(ImagePathWriterUtil.projectPath+"/images/car/back_"+dto.getRegNum()+".jpeg")));
            car.getCarImage().setSide(new ImagePathWriterUtil().imagePathWriter(dto.getCarImageDTO().getSide(), Paths.get(ImagePathWriterUtil.projectPath+"/images/car/side_"+dto.getRegNum()+".jpeg")));
            car.getCarImage().setInterior(new ImagePathWriterUtil().imagePathWriter(dto.getCarImageDTO().getInterior(), Paths.get(ImagePathWriterUtil.projectPath+"/images/car/interior_"+dto.getRegNum()+".jpeg")));

            carRepo.save(car);

        } catch (IOException e) {

            throw new RuntimeException(e);

        } catch (URISyntaxException e) {

            throw new RuntimeException(e);

        }


    }

    @Override
    public List<Car> getAllCars() throws RuntimeException {
        List<Car> map = modelMapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarAllDTO>>() {
        }.getType());

        return map;
    }

    @Override
    public CarAllDTO getCar(String regNum){
        if (!carRepo.existsById(regNum)){
            throw new RuntimeException(regNum+" : is Not Exists, Please Enter Valid RegNum !");
        }
        CarAllDTO carAllDTO = modelMapper.map(carRepo.findById(regNum), CarAllDTO.class);
        return carAllDTO;
    }

    @Override
    public Long countAvailableCar() {
        return null;
    }
}
