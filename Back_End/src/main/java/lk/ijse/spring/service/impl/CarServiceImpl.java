package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CarAllDTO;
import lk.ijse.spring.dto.CarDTO;
import lk.ijse.spring.dto.CarImageDTO;
import lk.ijse.spring.entity.Car;
import lk.ijse.spring.repo.CarRepo;
import lk.ijse.spring.repo.RentDetailsRepo;
import lk.ijse.spring.service.CarService;
import lk.ijse.spring.util.ImagePathWriterUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CarRepo carRepo;

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

        return modelMapper.map(carRepo.findAll(), new TypeToken<ArrayList<CarAllDTO>>() {
        }.getType());
    }

    @Override
    public CarAllDTO getCar(String regNum){
        if (!carRepo.existsById(regNum)){
            throw new RuntimeException(regNum+" : is Not Exists, Please Enter Valid RegNum !");
        }
        return modelMapper.map(carRepo.findById(regNum), CarAllDTO.class);
    }

    @Override
    public Long countAvailableCar() {
        if (carRepo.countAvailableCars()==0){
            throw new RuntimeException(" No Available Cars for now !");
        }
        return carRepo.countAvailableCars();
    }

    @Override
    public Long countReserveCarAmount() {
        if (carRepo.countReservedCars()==0){
            throw new RuntimeException("No Cars Reserved at the moment !");

        }
        return carRepo.countReservedCars();
    }

    @Override
    public void updateCar(CarDTO carDTO) {

        if(!carRepo.existsById(carDTO.getRegNum())){
            throw new RuntimeException(carDTO.getRegNum()+" : is Not Exists!");
        }

        Car carThatMustUpdate = carRepo.findById(carDTO.getRegNum()).get();
        Car mapped = modelMapper.map(carDTO, Car.class);

        mapped.setCarImage(carThatMustUpdate.getCarImage());
        carRepo.save(mapped);
    }

    @Override
    public void deleteCar(String regNum) {
        if (!carRepo.existsById(regNum)){
            throw new RuntimeException(regNum+" : is Not Exists!");
        }
//        carRepo.deleteById(regNum);

        detailsRepo.deleteRentDetailsByRegNum(regNum);
        carRepo.deleteById(regNum);
    }

    @Override
    public Long countMaintainingCarAmount() {
        if (carRepo.countMaintainingCars()==0){
            throw new RuntimeException("No cars in maintain at the moment!");
        }
       return carRepo.countReservedCars();
    }

    @Override
    public List countCarAmountByBrand() {
        return  carRepo.countCarBrands();
    }

    @Override
    public void editCarImages(CarImageDTO carImageDTO,CarDTO dto) {
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
    public List<CarAllDTO> filterCarsByRegNum(String text, String search, String fuel) {
        fuel = fuel.equals("ALL") ? "" : fuel;

        switch (search){
            case "REG_NUM":

                List<CarAllDTO> map1 = modelMapper.map(carRepo.findByRegNumLikeAndFuelTypeLike("%" + text + "%", "%" + fuel + "%"), new TypeToken<ArrayList<CarAllDTO>>() {
                }.getType());
                return map1;

            case "BRAND":
                List<CarAllDTO> map2 = modelMapper.map(carRepo.findByBrandLikeAndFuelTypeLike("%" + text + "%", "%" + fuel + "%"), new TypeToken<ArrayList<CarAllDTO>>() {
                }.getType());
                return map2;

            case "COLOR":
                List<CarAllDTO> map3 = modelMapper.map(carRepo.findByColorLikeAndFuelTypeLike("%" + text + "%", "%" + fuel + "%"), new TypeToken<ArrayList<CarAllDTO>>() {
                }.getType());
                return map3;

            default:
                return null;
        }
    }

    @Override
    public void moveCarToMaintain(String regNum) {
        if (!carRepo.existsById(regNum)){
            throw new RuntimeException(regNum+" : is Not Exists!");
        }
        Car carByRegNum = carRepo.findCarByRegNum(regNum);
        carByRegNum.setAvailability(carByRegNum.getAvailability().equals("YES")?"NO":"YES");
        carRepo.save(carByRegNum);
    }


}
