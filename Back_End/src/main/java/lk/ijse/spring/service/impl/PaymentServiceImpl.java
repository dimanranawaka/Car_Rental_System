package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.entity.Payment;
import lk.ijse.spring.entity.Rent;
import lk.ijse.spring.repo.PaymentRepo;
import lk.ijse.spring.repo.RentRepo;
import lk.ijse.spring.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PaymentRepo paymentRepo;
    @Autowired
    RentRepo rentRepo;

    @Override
    public void addPayment(PaymentDTO dto) {

        Payment map = modelMapper.map(dto, Payment.class);
        Rent rent = rentRepo.findById(dto.getRentId().getRentId()).get();

        map.setRentId(rent);
        map.setDate(LocalTime.now());
        map.setDate(LocalTime.now());

        paymentRepo.save(map);
    }
}
