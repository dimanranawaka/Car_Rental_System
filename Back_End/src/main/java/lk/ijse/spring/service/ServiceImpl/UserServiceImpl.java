package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getUser(String username, String password) {
        return null;
    }
}
