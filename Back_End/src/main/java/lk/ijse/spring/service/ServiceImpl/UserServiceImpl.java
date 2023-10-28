package lk.ijse.spring.service.ServiceImpl;

import lk.ijse.spring.dto.UserDTO;
import lk.ijse.spring.entity.User;
import lk.ijse.spring.repo.UserRepo;
import lk.ijse.spring.service.UserService;
import lk.ijse.spring.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public UserDTO getUser(String username, String password) {
        User user = userRepo.findById(username).get();
        if (!user.getPassword().equals(password)){
            throw new RuntimeException("Invalid Username or Password!");
        }
        UserDTO map = modelMapper.map(user, UserDTO.class);
        UserUtil.currentUser = map;
        return map;
    }
}
