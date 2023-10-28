package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.repo.RentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RentServiceImpl {
    @Autowired
    RentRepo rentRepo;
}
