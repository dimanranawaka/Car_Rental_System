package lk.ijse.spring.service;

import lk.ijse.spring.dto.PaymentDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {
    void addPayment(PaymentDTO dto) throws RuntimeException;

    List<PaymentDTO> getAllPayments() throws RuntimeException;

    List<PaymentDTO> getPaymentsByNic(String nic) throws RuntimeException;

    List getDailyIncome() throws RuntimeException;

    List getMonthlyIncome() throws RuntimeException;

    List getYearlyIncome() throws RuntimeException;

    BigDecimal getCurrentDayIncome() throws RuntimeException;

    BigDecimal getCurrentMonthIncome() throws RuntimeException;
}
