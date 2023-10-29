package lk.ijse.spring.service.impl;

import lk.ijse.spring.repo.RentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RentServiceImpl {
    @Autowired
    RentRepo rentRepo;
}
