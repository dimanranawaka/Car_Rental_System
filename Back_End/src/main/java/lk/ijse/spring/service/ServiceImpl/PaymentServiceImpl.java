package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.PaymentDTO;
import lk.ijse.spring.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentDTO addPayment(PaymentDTO dto) {
        return null;
    }
}
