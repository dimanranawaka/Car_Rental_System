package lk.ijse.spring.service;

import lk.ijse.spring.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    void addPayment(PaymentDTO dto) throws RuntimeException;

    List<PaymentDTO> getAllPayments() throws RuntimeException;
}
