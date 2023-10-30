package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.entity.Payment;
import lk.ijse.spring.entity.Rent;
import lk.ijse.spring.repo.PaymentRepo;
import lk.ijse.spring.repo.RentRepo;
import lk.ijse.spring.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public void addPayment(PaymentDTO dto) throws RuntimeException{

        Payment map = modelMapper.map(dto, Payment.class);
        Rent rent = rentRepo.findById(dto.getRentId().getRentId()).get();

        map.setRentId(rent);
        map.setDate(LocalTime.now());
        map.setDate(LocalTime.now());

        paymentRepo.save(map);
    }

    @Override
    public List<PaymentDTO> getAllPayments() throws RuntimeException {
        return modelMapper.map(paymentRepo.findAll(),new TypeToken<ArrayList<PaymentDTO>>(){}.getType());
    }

    @Override
    public List<PaymentDTO> getPaymentsByNic(String nic) throws RuntimeException {
        return modelMapper.map(paymentRepo.findAllByRentId_Nic_Nic(nic), new TypeToken<ArrayList<PaymentDTO>>(){}.getType());
    }

    @Override
    public List getDailyIncome() throws RuntimeException {
        return paymentRepo.getDailyIncome();
    }

    @Override
    public List getMonthlyIncome() throws RuntimeException {
        return paymentRepo.getMonthlyIncome();
    }

    @Override
    public List getYearlyIncome() throws RuntimeException {
        return paymentRepo.getYearlyIncome();
    }

    @Override
    public BigDecimal getCurrentDayIncome() throws RuntimeException {
        return paymentRepo.getCurrentDayIncome();
    }

    @Override
    public BigDecimal getCurrentMonthIncome() throws RuntimeException {
        return paymentRepo.getCurrentMonthIncome();
    }

    @Override
    public BigDecimal getCurrentYearIncome() throws RuntimeException {
        return paymentRepo.getCurrentYearIncome();
    }
}
