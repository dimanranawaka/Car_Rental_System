package lk.ijse.spring.service.impl;

import lk.ijse.spring.repo.RentRepo;
import lk.ijse.spring.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RentServiceImpl implements RentService {
    @Autowired
    RentRepo rentRepo;
}
